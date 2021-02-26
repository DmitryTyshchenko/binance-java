package ztysdmy.binance.lsv;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import ztysdmy.binance.BinanceApi;
import ztysdmy.binance.http.HttpBinanceApi;
import ztysdmy.binance.model.Trade;


public class PricePredictionDemo {

	@Test
	public void pricePrediction() throws Exception {
		for (int i=0;i<10;i++) {
		BinanceApi api = new HttpBinanceApi();
		var params = new HashMap<String, String>();
		params.put("limit", "1000");
		
		
		
		List<Trade> recentTrades = api.trades("BTCBUSD", params);
		
		double avgBuyPrice = 0.d;
		double avgSellPrice =0.d;
		
		double numberOfBuyers = 0;
		double numberOfSellers = 0;
		
		for (Trade trade:recentTrades) {
			
			double price = trade.getPrice().doubleValue();
			
			if (trade.getIsBuyerMaker()) {
				avgBuyPrice = avgBuyPrice + price;
				numberOfBuyers++;
			} else {
				avgSellPrice = avgSellPrice+price;
				numberOfSellers++;
			}
		}
		
		avgBuyPrice = avgBuyPrice/numberOfBuyers;
		avgSellPrice = avgSellPrice/numberOfSellers;
		
		System.out.println("Number of Buyers "+numberOfBuyers);
		System.out.println("Avg Buy Price "+avgBuyPrice);
		System.out.println("Number of Sellers "+numberOfSellers);
		System.out.println("Avg Sell Price "+avgSellPrice);
		System.out.println("Current Price "+api.price("BTCBUSD"));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Thread.currentThread().sleep(5000);
		}
	}
	
}
