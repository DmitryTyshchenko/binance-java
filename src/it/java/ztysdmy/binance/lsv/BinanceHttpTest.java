package ztysdmy.binance.lsv;


import org.junit.Test;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.http.BinanceException;
import ztysdmy.binance.http.HttpBinanceApi;

public class BinanceHttpTest {

	@Test
	public void getPrices() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		System.out.println(api.price("BTCBUSD"));
		
	}
	
	@Test
	public void getAllPrices() throws Exception {
		
		BinanceApi api = new HttpBinanceApi();
		//System.out.println(api.allPrices());
		
	}
	
	@Test
	public void allOrders() throws Exception {
		try {
		BinanceApi api = new HttpBinanceApi("asaf", "asd22");
		//System.out.println(api.allOrders("BTCBUSD"));
		} catch (BinanceException e) {
			System.out.println(e.getBinanceMessage());
		}
	}
}
