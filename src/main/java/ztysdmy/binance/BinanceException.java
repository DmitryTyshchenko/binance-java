package ztysdmy.binance;

public class BinanceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	int code;
	String binanaceMeddage;
	
	public BinanceException(int code, String binanceMessage) {
		this.code = code;
		this.binanaceMeddage = binanceMessage;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getBinanceMessage() {
		return this.binanaceMeddage;
	}
	
	@Override
	public String getMessage() {
		return this.getBinanceMessage();
	}
	
	@Override
	public String getLocalizedMessage() {
		return this.getBinanceMessage();
	}
}
