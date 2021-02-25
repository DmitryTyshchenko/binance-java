package ztysdmy.binance;

public class BinanceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	BinanceExceptionData exeptionData;
	
	public BinanceException(BinanceExceptionData exceptionData) {
		this.exeptionData = exceptionData;
	}
	
	public int getCode() {
		return exeptionData.getCode();
	}
	
	public String getBinanceMessage() {
		return exeptionData.getMsg();
	}
	
	@Override
	public String getMessage() {
		return this.getBinanceMessage();
	}
	
	@Override
	public String getLocalizedMessage() {
		return this.getBinanceMessage();
	}
	
	public static class BinanceExceptionData {
	
		private static final long serialVersionUID = 1L;
		int code;
		String msg;
		
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
	}
}
