package ztysdmy.binance.http;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import java.util.function.Function;

public enum RequestMethod {

	GET(HttpRequest.Builder::GET),
	POST(x->x.POST(BodyPublishers.noBody()));//, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
	
	private RequestMethod(Function<HttpRequest.Builder, HttpRequest.Builder> httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	private Function<HttpRequest.Builder, HttpRequest.Builder> httpMethod;
	
	public Function<HttpRequest.Builder, HttpRequest.Builder> builderWithMethod() {
		return this.httpMethod;
	}
}