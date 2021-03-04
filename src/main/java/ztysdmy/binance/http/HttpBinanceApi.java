package ztysdmy.binance.http;

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
import ztysdmy.binance.model.OrderResponse;
import ztysdmy.binance.model.OrderSide;
import ztysdmy.binance.model.OrderType;
import ztysdmy.binance.model.PriceTicker;
import ztysdmy.binance.model.TickerPriceChangeStatistics;
import ztysdmy.binance.model.Trade;

import static ztysdmy.binance.http.HttpUtility.*;
import static ztysdmy.binance.http.HMACEncoding.*;
import static ztysdmy.binance.http.Utility.*;

public class HttpBinanceApi implements BinanceApi {

	private String baseURL = "https://www.binance.com/api/v3/";

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
		return Arrays.asList(executeUnsignedRequest(queryEndpoint, params, Trade[].class));
	}

	@Override
	public PriceTicker price(String symbol) throws RequestLimitException, BinanceException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/price";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		return executeUnsignedRequest(queryEndpoint, params, PriceTicker.class);
	}

	@Override
	public List<PriceTicker> allPrices() throws RequestLimitException {
		var queryEndpoint = baseURL + "ticker/price";
		return Arrays.asList(executeUnsignedRequest(queryEndpoint, new HashMap<String, String>(), 
				PriceTicker[].class));
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
		var request = REQUEST(queryEndpoint, signedHeader(apiKey), params, RequestMethod.GET);
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
		var request = REQUEST(queryEndpoint, signedHeader(apiKey), params, RequestMethod.GET);
		var response = SEND(request);
		return Arrays.asList(responseBodyToObject(response.body(), Order[].class));
	}
	
	
	@Override
	public List<AggTradeData> aggTrades(String symbol, Map<String, String> params)
			throws RequestLimitException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "aggTrades";
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("symbol", symbol);
		return Arrays.asList(executeUnsignedRequest(queryEndpoint, params, AggTradeData[].class));
	}

	@Override
	public List<KLine> klines(String symbol, KlineInterval interval, Map<String, String> params)
			throws RequestLimitException {
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
	public AvgPrice avgPrice(String symbol) throws RequestLimitException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "avgPrice";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		return executeUnsignedRequest(queryEndpoint, params, AvgPrice.class);
	}

	@Override
	public TickerPriceChangeStatistics tickerPriceChangeStatistics(String symbol)
			throws RequestLimitException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/24hr";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		return executeUnsignedRequest(queryEndpoint, params, TickerPriceChangeStatistics.class);
	}

	@Override
	public OrderBookTicker bookTicker(String symbol) throws RequestLimitException {
		checkArguments(symbol);
		var queryEndpoint = baseURL + "ticker/bookTicker";
		var params = new HashMap<String, String>();
		params.put("symbol", symbol);
		return executeUnsignedRequest(queryEndpoint, params, OrderBookTicker.class);
	}

	@Override
	public Order order(String symbol, Map<String, String> params) throws RequestLimitException {
		checkArguments(symbol, params);

		if (params.get("orderId") == null && params.get("origClientOrderId") == null) {

			throw new IllegalArgumentException("Either orderId or origClientOrderId must be sent");
		}
		var queryEndpoint = baseURL + "order";
		params.put("symbol", symbol);
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = REQUEST(queryEndpoint, signedHeader(apiKey), params, RequestMethod.GET);
		var response = SEND(request);
		return responseBodyToObject(response.body(), Order.class);
	}

	private <T> T responseBodyToObject(String body, Class<T> clazz) {
		return new Gson().fromJson(body, clazz);
	}
	
	private <T> T executeUnsignedRequest(String queryEndpoint, Map<String, String> params, Class<T> clazz) 
			throws RequestLimitException {
		
		var response = createUnsignedGetRequestAndSend(queryEndpoint, params);
		return responseBodyToObject(response.body(), clazz);
	}
	
	private void checkArguments(Object... args) {
		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];
			if (Objects.isNull(arg)) {
				throw new IllegalArgumentException(Integer.toString(i) + " argument is illegal. Should not be a NULL");
			}
		}
	}

	@Override
	public Order newOrder(String symbol, OrderSide orderSide, OrderType orderType, Map<String, String> params)
			throws RequestLimitException {
		checkArguments(symbol, orderSide, orderType, params);
		var queryEndpoint = baseURL + "order";
		params.put("symbol", symbol);
		params.put("side", orderSide.toString());
		params.put("type", orderType.toString());
		params.put("newOrderRespType", OrderResponse.RESULT.toString());
		params = addTimeStampIfAbsentAndSignRequest(params);
		var request = REQUEST(queryEndpoint, signedHeader(apiKey), params, RequestMethod.POST);
		var response = SEND(request);
		return responseBodyToObject(response.body(), Order.class);
	}
}
