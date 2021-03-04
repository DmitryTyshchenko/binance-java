package ztysdmy.binance.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonParser;

import ztysdmy.binance.BinanceException;
import ztysdmy.binance.RequestLimitException;

import static java.util.stream.Collectors.joining;
import static ztysdmy.binance.http.Utility.*;

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

	static HttpRequest REQUEST(String queryEndpoint, Map<String, String> headers, 
			Map<String, String> params, RequestMethod requestMethod)
			throws RequestLimitException {

		Action<HttpRequest> buildRequest = () -> {
			var requestBuilder = HttpRequest.newBuilder().uri(buildUri(queryEndpoint, params));

			if (headers != null) {

				for (Entry<String, String> entry : headers.entrySet()) {
					requestBuilder = requestBuilder.header(entry.getKey(), entry.getValue());
				}
			}
			return requestMethod.setRequestMethodForBuilder().apply(requestBuilder).build();
		};

		return helper(() -> buildRequest.doAction());
	}

	static HttpResponse<String> SEND(HttpRequest request) throws RequestLimitException {
		var response = helper(() -> HttpClient.newBuilder().build().send(request, BodyHandlers.ofString()));

		if (response.statusCode() == 429 || response.statusCode() == 418) {
			// TODO get retry After from header
			throw new RequestLimitException(response.statusCode(), 10000);
		}

		if (response.statusCode() != 200) {
			throw createException(response.statusCode(), response.body());
		}
		return response;
	}

	static BinanceException createException(int code, String body) {
		try {
			// in case of Binance specific exception
			var jsonObject = JsonParser.parseString(body).getAsJsonObject();
			return new BinanceException(jsonObject.get("code").getAsInt(), jsonObject.get("msg").getAsString());
		} catch (Exception e) {
			return new BinanceException(code, body);
		}
	}

	static HttpResponse<String> createUnsignedGetRequestAndSend(String queryEndpoint, Map<String, String> params)
			throws RequestLimitException {
		var request = REQUEST(queryEndpoint, null, params, RequestMethod.GET);
		var response = SEND(request);
		return response;
	}

//	.header("X-MBX-APIKEY", apiKey)
//	.header("Content-Type", "application/x-www-form-urlencoded")

	static Map<String, String> signedHeader(String apiKey) {
		var result = new HashMap<String, String>();
		result.put("X-MBX-APIKEY", apiKey);
		result.put("Content-Type", "application/x-www-form-urlencoded");
		return result;
	}

}
