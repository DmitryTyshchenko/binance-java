package ztysdmy.binance.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;

import com.google.gson.Gson;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.model.PriceInfo;

public class HttpBinanceApi implements BinanceApi {

	private String baseURL = "https://www.binance.com/api/v3/";
	
	//timeout in seconds
	private int timeout = 2;

	@Override
	public PriceInfo price(String ticket) {

		var queryEndpoint = "ticker/price";
		
		var request = helper(() -> HttpRequest.newBuilder().uri(new URI(baseURL + queryEndpoint+ "?symbol=" + ticket))
				.timeout(Duration.ofSeconds(timeout)).GET().build());

		var response = helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));

		Gson gson = new Gson();
		return gson.fromJson(response.body(), PriceInfo.class);
	}

	@Override
	public List<PriceInfo> allPrices() {
		// TODO Auto-generated method stub
		return null;
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
