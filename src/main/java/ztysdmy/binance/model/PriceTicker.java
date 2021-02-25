package ztysdmy.binance.model;

import java.math.BigDecimal;

public class PriceTicker extends BinanceObject{

	private final String symbol;
	private final BigDecimal price;
	
	public PriceTicker(String symbol, BigDecimal price) {
		this.symbol = symbol;
		this.price = price;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
}
