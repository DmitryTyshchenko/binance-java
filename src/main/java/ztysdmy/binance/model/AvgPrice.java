package ztysdmy.binance.model;

import java.math.BigDecimal;

public class AvgPrice extends BinanceObject {

	int mins;
	BigDecimal price;
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
