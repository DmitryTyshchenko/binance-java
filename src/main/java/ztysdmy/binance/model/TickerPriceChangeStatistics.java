package ztysdmy.binance.model;

import java.math.BigDecimal;

public class TickerPriceChangeStatistics extends BinanceObject {

	String symbol;
	BigDecimal priceChange;
	BigDecimal priceChangePercent;
	BigDecimal weightedAvgPrice;
	BigDecimal prevClosePrice;
	BigDecimal lastPrice;
	BigDecimal lastQty;
	BigDecimal bidPrice;
	BigDecimal askPrice;
	BigDecimal openPrice;
	BigDecimal highPrice;
	BigDecimal lowPrice;
	BigDecimal volume;
	BigDecimal quoteVolume;
	long openTime;
	long closeTime;
	long firstId;
	long lastId;
	int  count;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}
	public BigDecimal getPriceChangePercent() {
		return priceChangePercent;
	}
	public void setPriceChangePercent(BigDecimal priceChangePercent) {
		this.priceChangePercent = priceChangePercent;
	}
	public BigDecimal getWeightedAvgPrice() {
		return weightedAvgPrice;
	}
	public void setWeightedAvgPrice(BigDecimal weightedAvgPrice) {
		this.weightedAvgPrice = weightedAvgPrice;
	}
	public BigDecimal getPrevClosePrice() {
		return prevClosePrice;
	}
	public void setPrevClosePrice(BigDecimal prevClosePrice) {
		this.prevClosePrice = prevClosePrice;
	}
	public BigDecimal getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}
	public BigDecimal getLastQty() {
		return lastQty;
	}
	public void setLastQty(BigDecimal lastQty) {
		this.lastQty = lastQty;
	}
	public BigDecimal getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}
	public BigDecimal getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}
	public BigDecimal getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}
	public BigDecimal getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}
	public BigDecimal getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getQuoteVolume() {
		return quoteVolume;
	}
	public void setQuoteVolume(BigDecimal quoteVolume) {
		this.quoteVolume = quoteVolume;
	}
	public long getOpenTime() {
		return openTime;
	}
	public void setOpenTime(long openTime) {
		this.openTime = openTime;
	}
	public long getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(long closeTime) {
		this.closeTime = closeTime;
	}
	public long getFirstId() {
		return firstId;
	}
	public void setFirstId(long firstId) {
		this.firstId = firstId;
	}
	public long getLastId() {
		return lastId;
	}
	public void setLastId(long lastId) {
		this.lastId = lastId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
