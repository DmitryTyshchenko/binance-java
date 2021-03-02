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
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.BinanceException;
import ztysdmy.binance.RequestLimitException;
import ztysdmy.binance.model.AggTradeData;
import ztysdmy.binance.model.AvgPrice;
import ztysdmy.binance.model.KLine;
import ztysdmy.binance.model.KlineInterval;
import ztysdmy.binance.model.Order;
import ztysdmy.binance.model.OrderBookTicker;
import ztysdmy.binance.model.PriceTicker;
import ztysdmy.binance.model.TickerPriceChangeStatistics;
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
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		var jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
		return Long.valueOf(jsonObject.get("serverTime").getAsString());
	}

	@Override
	public List<Trade> trades(String symbol, Map<String, String> params)
			throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "trades";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return Arrays.asList(responseBodyToObject(response.body(), Trade[].class));
	}

	@Override
	public PriceTicker price(String symbol) throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/price";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return responseBodyToObject(response.body(), PriceTicker.class);
	}

	@Override
	public List<PriceTicker> allPrices() throws RequestLimitException {
		var queryEndpoint = baseURL + "ticker/price";
		var response = createUnsignedGetRequestAndSend(queryEndpoint, new HashMap<>());
		return Arrays.asList(responseBodyToObject(response.body(), PriceTicker[].class));
	}

	private HttpResponse<String> createUnsignedGetRequestAndSend(String queryEndpoint, Map<String, String> params)
			throws RequestLimitException {
		var request = GET(queryEndpoint, params);
		var response = SEND(request);
		return response;
	}

	@Override
	public List<Order> allOrders(String symbol, Map<String, String> params) throws RequestLimitException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "allOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(responseBodyToObject(response.body(), Order[].class));
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
		checkArguments(symbol);
		var queryEndpoint = baseURL + "openOrders";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return Arrays.asList(responseBodyToObject(response.body(), Order[].class));
	}

	private HttpRequest GET(String queryEndpoint, Map<String, String> params) throws RequestLimitException {

		return helper(() -> HttpRequest.newBuilder()
				.uri(buildUri(queryEndpoint, params))
				.timeout(Duration.ofSeconds(timeout))
				.GET()
				.build());
	}

	private HttpRequest SIGNEDGET(String queryEndpoint, Map<String, String> params) throws RequestLimitException {

		return helper(() -> HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params))
				.header("X-MBX-APIKEY", apiKey)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.timeout(Duration.ofSeconds(timeout))
				.GET()
				.build());
	}

	private HttpResponse<String> SEND(HttpRequest request) throws RequestLimitException {
		var response = helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));

		if (response.statusCode() == 429 || response.statusCode() == 418) {
			// TODO get retry After from header
			throw new RequestLimitException(response.statusCode(), 10000);
		}

		if (response.statusCode() != 200) {
			throw createException(response.statusCode(), response.body());
		}
		return response;
	}

	private BinanceException createException(int code, String body) {
		try {
			// in case of Binance specific exception
			var jsonObject = JsonParser.parseString(body).getAsJsonObject();
			return new BinanceException(jsonObject.get("code").getAsInt(), jsonObject.get("msg").getAsString());
		} catch (Exception e) {
			return new BinanceException(code, body);
		}
	}

	@FunctionalInterface
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
	public List<AggTradeData> aggTrades(String symbol, Map<String, String> params)
			throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "aggTrades";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return Arrays.asList(responseBodyToObject(response.body(), AggTradeData[].class));
	}

	@Override
	public List<KLine> klines(String symbol, KlineInterval interval, Map<String, String> params)
			throws RequestLimitException, BinanceException {
		checkArguments(symbol, interval);
		var queryEndpoint = baseURL + "klines";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		params.put("interval", interval.value());
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
	
		var arrays = responseBodyToObject(response.body(), String[][].class);

		var result = new ArrayList<KLine>();

		for (String[] array : arrays) {
			result.add(KLine.fromStringArray(array));
		}
		return result;
	}

	@Override
	public AvgPrice avgPrice(String symbol) throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "avgPrice";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return responseBodyToObject(response.body(), AvgPrice.class);
	}

	@Override
	public TickerPriceChangeStatistics tickerPriceChangeStatistics(String symbol)
			throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/24hr";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return responseBodyToObject(response.body(), TickerPriceChangeStatistics.class);
	}

	@Override
	public OrderBookTicker bookTicker(String symbol) throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/bookTicker";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return responseBodyToObject(response.body(), OrderBookTicker.class);
	}

	@Override
	public Order order(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException {
		checkArguments(symbol, params);

		if (params.get("orderId") == null && params.get("origClientOrderId") == null) {

			throw new IllegalArgumentException("Either orderId or origClientOrderId must be sent");
		}

		var queryEndpoint = baseURL + "order";

		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = SIGNEDGET(queryEndpoint, params);
		var response = SEND(request);
		return responseBodyToObject(response.body(), Order.class);
	}

	private <T> T responseBodyToObject(String body, Class<T> clazz) {
		return new Gson().fromJson(body, clazz);
	}
	
	private void checkArguments(Object... args) {
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			if (Objects.isNull(arg)) {
				throw new IllegalArgumentException(Integer.toString(i) + " argument is illegal. Should not be a NULL");
			}
		}
	}
}
