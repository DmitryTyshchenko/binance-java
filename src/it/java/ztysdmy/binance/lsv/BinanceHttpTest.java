package ztysdmy.binance.lsv;


import java.util.HashMap;

import org.junit.Test;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.BinanceException;
import ztysdmy.binance.RequestLimitException;
import ztysdmy.binance.http.HttpBinanceApi;

public class BinanceHttpTest {

	@Test
	public void getPrices() throws Exception {
		try {
		BinanceApi api = new HttpBinanceApi();
		System.out.println(api.price("BTCBUSD"));
		} catch (RequestLimitException e) {
			
		}
	}
	
	//@Test
	public void getAllPrices() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		//System.out.println(api.allPrices());
		
	}
	
	//@Test
	public void allOrders() throws Exception {
		try {
		BinanceApi api = new HttpBinanceApi("",
				"");
		System.out.println(api.allOrders("BTCBUSD", null).toString());
		} catch (BinanceException e) {
			System.out.println(e.getBinanceMessage());
		}
	}
	
	//@Test
	public void openOrders() throws Exception {
		try {
		BinanceApi api = new HttpBinanceApi("",
				"");
		System.out.println(api.openOrders("BTCBUSD",null).toString());
		} catch (RequestLimitException e) {
			
		}
	}
}
