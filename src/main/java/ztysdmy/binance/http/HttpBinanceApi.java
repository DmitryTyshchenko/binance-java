package ztysdmy.binance.http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.BinanceException;
import ztysdmy.binance.BinanceException.BinanceExceptionData;
import ztysdmy.binance.RequestLimitException;
import ztysdmy.binance.model.AggTradeData;
import ztysdmy.binance.model.AvgPrice;
import ztysdmy.binance.model.KLine;
import ztysdmy.binance.model.KlineInterval;
import ztysdmy.binance.model.Order;
import ztysdmy.binance.model.PriceTicker;
import ztysdmy.binance.model.Trade;

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
	public Long checkServerTime() throws RequestLimitException, BinanceException {
		var queryEndpoint = baseURL + "time";
		var params = new HashMap<String, String>();
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
		return Long.valueOf(jsonObject.get("serverTime").getAsString());
	}

	@Override
	public List<Trade> trades(String symbol, Map<String, String> params)
			throws RequestLimitException, BinanceException {
		var queryEndpoint = baseURL + "trades";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		return Arrays.asList(new Gson().fromJson(response.body(), Trade[].class));
	}

	@Override
	public PriceTicker price(String symbol) throws RequestLimitException {
		var queryEndpoint = baseURL + "ticker/price";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		return new Gson().fromJson(response.body(), PriceTicker.class);
	}

	@Override
	public List<PriceTicker> allPrices() throws RequestLimitException {
		var queryEndpoint = baseURL + "ticker/price";
		var response = createUnsignedRequestAndSend(queryEndpoint, new HashMap<>());
		return Arrays.asList(new Gson().fromJson(response.body(), PriceTicker[].class));
	}

	private HttpResponse<String> createUnsignedRequestAndSend(String queryEndpoint, Map<String, String> params)
			throws RequestLimitException {
		var request = GET(queryEndpoint, params);
		var response = SEND(request);
		return response;
	}

	@Override
	public List<Order> allOrders(String symbol, Map<String, String> params) throws RequestLimitException {
		var queryEndpoint = baseURL + "allOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(new Gson().fromJson(response.body(), Order[].class));
	}

	private Map<String, String> addTimeStampIfAbsentAndSignRequest(Map<String, String> params)
			throws RequestLimitException {

		params.computeIfAbsent("timestamp", k -> String.valueOf(new Date().getTime()));
		var concatedParams = concatParams(params);
		var encodedParams = helper(() -> encode(secretKey, concatedParams));
		params.put("signature", encodedParams);
		return params;
	}

	@Override
	public List<Order> openOrders(String symbol, Map<String, String> params) throws RequestLimitException {
		var queryEndpoint = baseURL + "openOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(new Gson().fromJson(response.body(), Order[].class));
	}

	private HttpRequest GET(String queryEndpoint, Map<String, String> params) throws RequestLimitException {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params))
				.timeout(Duration.ofSeconds(timeout)).GET().build());
	}

	private HttpRequest SIGNEDGET(String queryEndpoint, Map<String, String> params) throws RequestLimitException {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params)).header("X-MBX-APIKEY", apiKey)
				.header("Content-Type", "application/x-www-form-urlencoded").timeout(Duration.ofSeconds(timeout)).GET()
				.build());
	}

	private HttpResponse<String> SEND(HttpRequest request) throws RequestLimitException {
		var response = helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));

		if (response.statusCode() == 429 || response.statusCode() == 418) {
			// TODO get retry After from header
			throw new RequestLimitException(response.statusCode(), 10000);
		}

		if (response.statusCode() != 200) {
			throw new BinanceException(new Gson().fromJson(response.body(), BinanceExceptionData.class));
		}
		return response;
	}

	private static interface Action<T> {
		T doAction() throws Exception;
	}

	private <T> T helper(Action<T> a) throws RequestLimitException {
		try {
			return a.doAction();
		} catch (RequestLimitException e) {
			throw e;
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<AggTradeData> aggTrades(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException {
		var queryEndpoint = baseURL + "aggTrades";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		return Arrays.asList(new Gson().fromJson(response.body(), AggTradeData[].class));
	}

	@Override
	public List<KLine> klines(String symbol, KlineInterval interval, Map<String, String> params) throws RequestLimitException, BinanceException {
		var queryEndpoint = baseURL + "klines";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params.put("interval", interval.value());
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		var arrays = new Gson().fromJson(response.body(), String[][].class);
		
		var result = new ArrayList<KLine>();
		
		for (String[] array:arrays) {
			result.add(KLine.fromStringArray(array));
		}
		
		return result;
	}

	@Override
	public AvgPrice avgPrice(String symbol) throws RequestLimitException, BinanceException {
		var queryEndpoint = baseURL + "avgPrice";
		var	params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedRequestAndSend(queryEndpoint, params);
		return new Gson().fromJson(response.body(), AvgPrice.class);
	}

}
