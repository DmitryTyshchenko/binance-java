package ztysdmy.binance.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import static java.util.stream.Collectors.joining;

class HttpUtility {

	private HttpUtility() {
	}

	static URI buildUri(String baseUri, Map<String, String> params) throws URISyntaxException {
		var uri = params.size() > 0 ? baseUri + "?" + concatParams(params) : baseUri;
		return new URI(uri);
	}

	static String concatParams(Map<String, String> params) {
		return params.entrySet().stream().map(Object::toString).collect(joining("&"));
	}
}
