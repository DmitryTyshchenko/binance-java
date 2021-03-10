package ztysdmy.binance.model;

public class RateLimiters {
	
	RateLimitType rateLimitType;
	RateLimitIntervals interval;
	int  intervalNum;
	int  limit;
	
	public RateLimitType getRateLimitType() {
		return rateLimitType;
	}

	public void setRateLimitType(RateLimitType rateLimitType) {
		this.rateLimitType = rateLimitType;
	}

	public RateLimitIntervals getInterval() {
		return interval;
	}

	public void setInterval(RateLimitIntervals interval) {
		this.interval = interval;
	}

	public int getIntervalNum() {
		return intervalNum;
	}

	public void setIntervalNum(int intervalNum) {
		this.intervalNum = intervalNum;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public static enum RateLimitIntervals {
		SECOND, MINUTE, DAY;
	}
	
	public static enum RateLimitType {
		REQUEST_WEIGHT, ORDERS, RAW_REQUESTS;
	}
}
