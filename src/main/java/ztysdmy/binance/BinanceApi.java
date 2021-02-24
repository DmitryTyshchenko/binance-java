package ztysdmy.binance;

import java.util.List;

import ztysdmy.binance.model.PriceTicker;

public interface BinanceApi {

	/**
	 * Returns latest price for all symbols.
	 * @return
	 */
	List<PriceTicker> allPrices();
	
	/**
	 * Returns latest price for a symbol.
	 * @param symbol (BTCBUSD, etc)
	 * @return
	 */
	PriceTicker price(String symbol);
	
	String allOrders(String symbol);
}
