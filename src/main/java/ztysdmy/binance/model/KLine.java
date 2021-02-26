package ztysdmy.binance.model;

import java.math.BigDecimal;

public class KLine extends BinanceObject {
	
	long openTime;       // Open time
	BigDecimal open;     // Open
	BigDecimal high;     // High
	BigDecimal low;      // Low
	BigDecimal close;    // Close
	BigDecimal volume;   // Volume
	long closeTime;      // Close time
	BigDecimal quoteAssetVolume; // Quote asset volume
	int numberOfTrades; // Number of trades
	BigDecimal takerBuyBase; // Taker buy base asset volume
	BigDecimal takerBuyQuote;    // Taker buy quote asset volume
	BigDecimal ignore; // Ignore.
	public long getOpenTime() {
		return openTime;
	}
	public void setOpenTime(long openTime) {
		this.openTime = openTime;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public long getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(long closeTime) {
		this.closeTime = closeTime;
	}
	public BigDecimal getQuoteAssetVolume() {
		return quoteAssetVolume;
	}
	public void setQuoteAssetVolume(BigDecimal quoteAssetVolume) {
		this.quoteAssetVolume = quoteAssetVolume;
	}
	public int getNumberOfTrades() {
		return numberOfTrades;
	}
	public void setNumberOfTrades(int numberOfTrades) {
		this.numberOfTrades = numberOfTrades;
	}
	public BigDecimal getTakerBuyBase() {
		return takerBuyBase;
	}
	public void setTakerBuyBase(BigDecimal takerBuyBase) {
		this.takerBuyBase = takerBuyBase;
	}
	public BigDecimal getTakerBuyQuote() {
		return takerBuyQuote;
	}
	public void setTakerBuyQuote(BigDecimal takerBuyQuote) {
		this.takerBuyQuote = takerBuyQuote;
	}
	public BigDecimal getIgnore() {
		return ignore;
	}
	public void setIgnore(BigDecimal ignore) {
		this.ignore = ignore;
	}
	
	public static KLine fromStringArray(String[] array) {
		var result = new KLine();
		
		result.setOpenTime(Long.valueOf(array[0]));
		result.setOpen(BigDecimal.valueOf(Double.valueOf(array[1])));
		result.setHigh(BigDecimal.valueOf(Double.valueOf(array[2])));
		result.setLow(BigDecimal.valueOf(Double.valueOf(array[3])));

		result.setClose(BigDecimal.valueOf(Double.valueOf(array[4])));
		result.setVolume(BigDecimal.valueOf(Double.valueOf(array[5])));
		result.setCloseTime(Long.valueOf(array[6]));
		result.setQuoteAssetVolume(BigDecimal.valueOf(Double.valueOf(array[7])));
		result.setNumberOfTrades(Integer.valueOf(array[8]));
		result.setTakerBuyBase(BigDecimal.valueOf(Double.valueOf(array[9])));
		result.setTakerBuyQuote(BigDecimal.valueOf(Double.valueOf(array[10])));
		result.setIgnore(BigDecimal.valueOf(Double.valueOf(array[11])));
	    return result;
	}
}
