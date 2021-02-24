package ztysdmy.binance;

import java.util.List;

import ztysdmy.binance.model.PriceInfo;

public interface BinanceApi {

	/**
	 * Returns latest price for all symbols.
	 * @return
	 */
	List<PriceInfo> allPrices();
	
	/**
	 * Returns latest price for a symbol.
	 * @param ticket (BTCBUSD, etc)
	 * @return
	 */
	PriceInfo price(String ticket);
	
	String allOrders();
}
