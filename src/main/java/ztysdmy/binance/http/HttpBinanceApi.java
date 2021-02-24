package ztysdmy.binance.http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.http.BinanceException.BinanceExceptionData;
import ztysdmy.binance.model.Order;
import ztysdmy.binance.model.PriceTicker;
import static ztysdmy.binance.http.HttpUtility.*;
import static ztysdmy.binance.http.HMACEncoding.*;

public class HttpBinanceApi implements BinanceApi {

	private String baseURL = "https://www.binance.com/api/v3/";

	// timeout in seconds
	private int timeout = 2;

	private String apiKey = "";
	private String secretKey = "";

	public HttpBinanceApi() {
	}

	public HttpBinanceApi(String apiKey, String secretKey) {
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}

	@Override
	public PriceTicker price(String symbol) {
		var queryEndpoint = baseURL + "ticker/price";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var request = GET(queryEndpoint, params);
		var response = SEND(request);
		return new Gson().fromJson(response.body(), PriceTicker.class);
	}

	@Override
	public List<PriceTicker> allPrices() {
		var queryEndpoint = baseURL + "ticker/price";
		var request = GET(queryEndpoint, new HashMap<>());
		var response = SEND(request);
		return Arrays.asList(new Gson().fromJson(response.body(), PriceTicker[].class));
	}

	@Override
	public List<Order> allOrders(String symbol, Map<String, String> params) {
		var queryEndpoint = baseURL + "allOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params.computeIfAbsent("timestamp", k -> String.valueOf(new Date().getTime()));
		var concatedParams = concatParams(params);
		var encodedParams = helper(() -> encode(secretKey, concatedParams));
		params.put("signature", encodedParams);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(new Gson().fromJson(response.body(), Order[].class));
	}

	@Override
	public List<Order> openOrders(String symbol, Map<String, String> params) {
		var queryEndpoint = baseURL + "openOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params.computeIfAbsent("timestamp", k -> String.valueOf(new Date().getTime()));
		var concatedParams = concatParams(params);
		var encodedParams = helper(() -> encode(secretKey, concatedParams));
		params.put("signature", encodedParams);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(new Gson().fromJson(response.body(), Order[].class));
	}

	private HttpRequest GET(String queryEndpoint, Map<String, String> params) {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params))
				.timeout(Duration.ofSeconds(timeout)).GET().build());
	}

	private HttpRequest SIGNEDGET(String queryEndpoint, Map<String, String> params) {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params)).header("X-MBX-APIKEY", apiKey)
				.header("Content-Type", "application/x-www-form-urlencoded").timeout(Duration.ofSeconds(timeout)).GET()
				.build());
	}

	private HttpResponse<String> SEND(HttpRequest request) {
		var response = helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));
		if (response.statusCode() != 200) {
			throw new BinanceException(new Gson().fromJson(response.body(), BinanceExceptionData.class));
		}
		return response;
	}

	private static interface Action<T> {
		T doAction() throws Exception;
	}

	private <T> T helper(Action<T> a) {
		try {
			return a.doAction();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
