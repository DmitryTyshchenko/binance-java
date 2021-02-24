package ztysdmy.binance.lsv;


import org.junit.Test;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.http.HttpBinanceApi;

public class BinanceHttpTest {

	//@Test
	public void getPrices() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		System.out.println(api.price("BTCBUSD"));
		
	}
	
	//@Test
	public void getAllPrices() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		//System.out.println(api.allPrices());
		
	}
	
	@Test
	public void allOrders() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		System.out.println(api.allOrders("BTCBUSD"));
	}
}
