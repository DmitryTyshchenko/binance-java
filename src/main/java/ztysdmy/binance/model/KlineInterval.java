package ztysdmy.binance.model;

public enum KlineInterval {
	
	one_minute("1m"), three_minutes("3m"), five_minutes("5m"),
	fifteen_minutes("15m"), thirty_minutes("30m"), one_hour("1h"),
	two_hours("2h"), four_hours("4h"), six_hours("6h"), twelve_hours("12h"),
	one_day("1d"), one_week("1w"), one_month("1M");

	private KlineInterval(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String value() {
		return this.value;
	}
}
