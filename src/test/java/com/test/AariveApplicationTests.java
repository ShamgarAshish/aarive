package com.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.client.utils.URIBuilder;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AariveApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testStatusCode() throws ClientProtocolException, IOException, JSONException, URISyntaxException {

		String[] getApis = { "/PoolOrderList", "/PoolOrderDetailsSv", "/PoolOrderDetailsUs", "/PoolOrderDetailsBfl",
				"/PoolOrderActivity", "/AarDocs", "/AarBlobFile", "/PriorityLov", "/DestinationLov", "/AtaChapterLov",
				"/InstSerialNumLov", "/TimeZoneLov", "/TailNumLov", "/CarrierMethod", "/CountryLov", "/StateLov",
				"/PartVerification", "/ActionReqOffCoreDtl", "/ActReqWaybillDtl" };
		String[] postApis = { "/PoolOrderCount", "/UploadDoc", "/PoolOrderList" };
		for (String string : getApis) {
			System.out.println(string);
			System.out.println(new URL("https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"));
			Map getCalls = TestScenario.GetCalls(new URL("https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"),
					string+"?", null);
			Set<String> keySet = getCalls.keySet();
			for (String s : keySet) {
				System.out.println(s);
				System.out.println(getCalls.get(s));
				System.out.println(Integer.parseInt((String) getCalls.get("key")));
				assertThat(Integer.parseInt((String) getCalls.get("key")))
						.isEqualTo(200);
			}
		}
		Map postCalls = TestScenario.postCalls(new URL("https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"),
				"PoolOrderCount");
		Set<String> keySet1 = postCalls.keySet();
		for (String s : keySet1) {
			System.out.println(s);
			System.out.println(postCalls.get(s));
			System.out.println(Integer.parseInt((String) postCalls.get("key")));
			assertThat(Integer.parseInt((String) postCalls.get("key")))
					.isEqualTo(200);
		}
		
	}
}
