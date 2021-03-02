package ztysdmy.binance;

import java.util.List;
import java.util.Map;

import ztysdmy.binance.model.AggTradeData;
import ztysdmy.binance.model.AvgPrice;
import ztysdmy.binance.model.KLine;
import ztysdmy.binance.model.KlineInterval;
import ztysdmy.binance.model.Order;
import ztysdmy.binance.model.OrderBookTicker;
import ztysdmy.binance.model.PriceTicker;
import ztysdmy.binance.model.TickerPriceChangeStatistics;
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
	 * Get compressed, aggregate trades. Trades that fill at the time, from the same taker order, 
	 * with the same price will have the quantity aggregated.
	 * @param symbol
	 * @param params
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	
	List<AggTradeData> aggTrades(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException;
	
	/**
	 * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
	 * @param symbol
	 * @param interval
	 * @param params
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	List<KLine> klines(String symbol, KlineInterval interval, Map<String, String> params) throws RequestLimitException, BinanceException;
	
	/**
	 * Current average price for a symbol.
	 * 
	 * @param symbol
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	AvgPrice avgPrice(String symbol) throws RequestLimitException, BinanceException;
	
	/**
	 * 24 hour rolling window price change statistics.
	 * @param symbol
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	TickerPriceChangeStatistics tickerPriceChangeStatistics(String symbol) throws RequestLimitException, BinanceException;
	
	/**
	 * Best price/qty on the order book for a symbol or symbols.
	 * @param symbol
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	OrderBookTicker bookTicker(String symbol) throws RequestLimitException, BinanceException;
	
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
	
	/**
	 * Query order
	 * Either orderId or origClientOrderId must be sent.
	 * @param symbol
	 * @param params
	 * @return
	 * @throws RequestLimitException
	 * @throws BinanceException
	 */
	Order order(String symbol, Map<String, String> params) throws RequestLimitException, BinanceException;
}
