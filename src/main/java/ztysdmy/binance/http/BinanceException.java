package ztysdmy.binance.http;

public class BinanceException extends RuntimeException {

	BinanceExceptionData exeptionData;
	
	public BinanceException(BinanceExceptionData exceptionData) {
		this.exeptionData = exceptionData;
	}
	
	public int getCode() {
		return exeptionData.getCode();
	}
	
	public String getMsg() {
		return exeptionData.getMsg();
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
