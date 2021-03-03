package ztysdmy.binance.http;

import ztysdmy.binance.RequestLimitException;

public class Utility {

	private Utility() {}
	
	@FunctionalInterface
	static interface Action<T> {
		T doAction() throws Exception;
	}

	static <T> T helper(Action<T> a) throws RequestLimitException {
		try {
			return a.doAction();
		} catch (RequestLimitException e) {
			throw e;
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
