package ztysdmy.binance.model;

import java.math.BigDecimal;

import com.google.gson.Gson;

public class PriceInfo {

	private final String symbol;
	private final BigDecimal price;
	
	public PriceInfo(String symbol, BigDecimal price) {
		this.symbol = symbol;
		this.price = price;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
