package ztysdmy.binance.http;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class HttpUtilityTest {

	@Test
	public void testCreateUriStringWithParams() throws Exception {
		var params = new HashMap<String, String>();
		params.put("param1", "value1");
		params.put("param2", "value2");
		var result = HttpUtility.createUriStringWithParams("test", params);
		Assert.assertTrue(result.equals("test?param1=value1&param2=value2"));
	}
}
