package ztysdmy.binance.http;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

class HMACEncoding {

	private HMACEncoding() {}
	
	static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }
}
