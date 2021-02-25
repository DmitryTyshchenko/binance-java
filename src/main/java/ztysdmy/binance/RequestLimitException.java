package ztysdmy.binance;

public class RequestLimitException extends Exception {
	
	private static final long serialVersionUID = 1L;
	int code;
	long retryAfter;
	
	public RequestLimitException(int code, long retryAfter) {
		
		this.code = code;
		this.retryAfter = retryAfter;
	}

	public int getCode() {
		return code;
	}

	public long getRetryAfter() {
		return retryAfter;
	}
	
}
