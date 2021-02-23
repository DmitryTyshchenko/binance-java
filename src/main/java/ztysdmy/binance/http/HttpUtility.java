package ztysdmy.binance.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class HttpUtility {

	private HttpUtility() {
	}

	public static URI buildUri(String baseUri, Map<String, String> params) throws URISyntaxException {

		URI result = new URI(baseUri);
		return result;
	}
}
