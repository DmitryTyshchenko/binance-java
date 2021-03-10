package ztysdmy.binance.lsv;

import java.util.HashMap;

import org.junit.Test;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.BinanceException;
import ztysdmy.binance.RequestLimitException;
import ztysdmy.binance.http.HttpBinanceApi;
import ztysdmy.binance.model.KlineInterval;
import ztysdmy.binance.model.OrderResponse;
import ztysdmy.binance.model.OrderSide;
import ztysdmy.binance.model.OrderType;

public class BinanceHttpTest {

	// @Test
	public void checkServerTime() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			System.out.println(api.checkServerTime());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testTrades() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			var params = new HashMap<String, String>();
			params.put("limit", "10");
			System.out.println(api.trades("BTCBUSD", params).toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testTickerPriceChangeStatistics() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			System.out.println(api.tickerPriceChangeStatistics("BTCBUSD").toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testBookTicker() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			System.out.println(api.bookTicker("BTCBUSD").toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testAvgPrice() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			System.out.println(api.avgPrice("BTCBUSD").toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testAggTrades() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			var params = new HashMap<String, String>();
			System.out.println(api.aggTrades("BTCBUSD", params).toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testKlines() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi();
			var params = new HashMap<String, String>();
			System.out.println(api.klines("BTCBUSD", KlineInterval.fifteen_minutes, params).toString());
		} catch (RequestLimitException e) {

		}
	}

	//@Test
	public void testPrice() throws Exception {
		BinanceApi api = new HttpBinanceApi();
		var price = api.price("BTCBUSD");
		System.out.println(price.toString());
	}

	// @Test
	public void getAllPrices() throws Exception {

		BinanceApi api = new HttpBinanceApi();
		System.out.println(api.allPrices());

	}

	// @Test
	public void allOrders() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			System.out.println(api.allOrders("BTCBUSD", null).toString());
		} catch (BinanceException e) {
			System.out.println(e.getBinanceMessage());
		}
	}

	// "orderId":1798255236,"orderListId":-1,"clientOrderId":"and_64b9e6ef2fce4860a7a042c0ccbff529"
	// @Test
	public void openOrders() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			System.out.println(api.openOrders("BTCBUSD", null).toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testOrder() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			var params = new HashMap<String, String>();
			params.put("orderId", "1798255236");
			System.out.println(api.order("BTCBUSD", params).toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test 1905908156
	public void testNewOrder() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			var params = new HashMap<String, String>();
			params.put("quantity", "0.001");
			params.put("price", "35000");
			System.out.println(api.newOrder("BTCBUSD", OrderSide.BUY, OrderType.LIMIT, params).toString());
		} catch (RequestLimitException e) {

		}
	}

	// @Test
	public void testCancelOrder() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			var params = new HashMap<String, String>();
			params.put("orderId", "1905908156");
			System.out.println(api.cancelOrder("BTCBUSD", params).toString());
		} catch (RequestLimitException e) {

		}
	}

	@Test
	public void testExchangeInfo() throws Exception {
		try {
			BinanceApi api = new HttpBinanceApi("", "");
			System.out.println(api.exchangeInfo().toString());
		} catch (RequestLimitException e) {

		}
	}

}
