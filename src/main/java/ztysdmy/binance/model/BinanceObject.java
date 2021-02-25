package ztysdmy.binance.model;

import com.google.gson.Gson;

public abstract class BinanceObject {

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
