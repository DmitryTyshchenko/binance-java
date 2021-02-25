package ztysdmy.binance.model;

import java.math.BigDecimal;

public class Trade extends BinanceObject {

	long id;
    BigDecimal price;
    BigDecimal qty;
    BigDecimal quoteQty;
    Long time;
    Boolean isBuyerMaker;
    Boolean isBestMatch;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	public BigDecimal getQuoteQty() {
		return quoteQty;
	}
	public void setQuoteQty(BigDecimal quoteQty) {
		this.quoteQty = quoteQty;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Boolean getIsBuyerMaker() {
		return isBuyerMaker;
	}
	public void setIsBuyerMaker(Boolean isBuyerMaker) {
		this.isBuyerMaker = isBuyerMaker;
	}
	public Boolean getIsBestMatch() {
		return isBestMatch;
	}
	public void setIsBestMatch(Boolean isBestMatch) {
		this.isBestMatch = isBestMatch;
	}
    
}
