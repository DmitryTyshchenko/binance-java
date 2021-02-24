package ztysdmy.binance.http;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.model.PriceInfo;
import static ztysdmy.binance.http.HttpUtility.*;

public class HttpBinanceApi implements BinanceApi {

	private String baseURL = "https://www.binance.com/api/v3/";

	// timeout in seconds
	private int timeout = 2;

	@Override
	public PriceInfo price(String ticket) {

		var queryEndpoint = baseURL + "ticker/price";

		var params = new HashMap<String, String>();
		params.put("symbol", ticket);

		var request = GET(queryEndpoint, params);

		var response = SEND(request);

		return new Gson().fromJson(response.body(), PriceInfo.class);
	}

	@Override
	public List<PriceInfo> allPrices() {

		var queryEndpoint = baseURL + "ticker/price";

		var request = GET(queryEndpoint, new HashMap<>());

		var response = SEND(request);

		return Arrays.asList(new Gson().fromJson(response.body(), PriceInfo[].class));
	}

	private HttpRequest GET(String queryEndpoint, HashMap<String, String> params) {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params))
				.timeout(Duration.ofSeconds(timeout)).GET().build());
	}

	private HttpResponse<String> SEND(HttpRequest request) {
		return helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));
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
