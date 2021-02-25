package ztysdmy.binance;

import java.util.List;
import java.util.Map;

import ztysdmy.binance.model.Order;
import ztysdmy.binance.model.PriceTicker;
import ztysdmy.binance.model.Trade;

public interface BinanceApi {

	/**
	 * Test connectivity to the Rest API and get the current server time.
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	
	Long checkServerTime() throws RequestLimitException, BinanceException;
	
	/**
	 * Recent trades list
	 */
	
	List<Trade> trades(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException; 
	
	/**
	 * Returns latest price for all symbols.
	 * @return
	 */
	List<PriceTicker> allPrices() throws RequestLimitException, BinanceException;
	
	/**
	 * Returns latest price for a symbol.
	 * @param symbol (BTCBUSD, etc)
	 * @return
	 */
	PriceTicker price(String symbol) throws RequestLimitException, BinanceException;
	
	/**
	 * Get all account orders; active, canceled, or filled.
	 * @param symbol
	 * @param params Request Optional Params
	 * @return
	 */
	List<Order> allOrders(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException;
	
	/**
	 * Get all open orders on a symbol If the symbol is not sent, orders for all
	 * symbols will be returned in an array.
	 * 
	 * @param params
	 * @return
	 */
	List<Order> openOrders(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException;
}
