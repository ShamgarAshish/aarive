package com.test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestScenario {

	//server "https://devws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"
	//server new URL(server)
	//QueryApi ="PoolOrderList?"
	//api = "PoolOrderList?"
	// if devws = "Basic YWxleC53YWx3b3J0aDphbGV4LndhbHdvcnRo"
	
	public static String querySuffix = "q=Email=Tarun.Khandelwal@aarhub.com&totalResults=true";
	//public static String user = "Basic YWxleC53YWx3b3J0aDphbGV4LndhbHdvcnRo";
	public static String user = "Basic Y2Fhcml2ZTpNVlBhcGlBdXRoNjEwMiE=";
	public static String contentType = "application/vnd.oracle.adf.action+json";
	public static String urls = "https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/";
	public static Map GetCalls(URL server,String api,String searchString) throws ClientProtocolException, IOException, JSONException{
		Map<String,String> resp= new HashMap();
		String string2 ;//= new URL(server,api+querySuffix).toString();
		if(searchString == null){
			System.out.println(new URL("https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"));
		string2 = new URL("https://testws.aarcorp.com/adfbc-b2b-aarive/rest/r1/"+api+querySuffix).toString();
		System.out.println(new URL(new URL(server.toString()),api+querySuffix));
		}else{
		string2 = new URL(server,api+searchString+querySuffix).toString();
		}
		System.out.println(string2);
		HttpUriRequest request = new HttpGet(string2);
		request.setHeader("Authorization", user);
		request.setHeader("Content-Type", contentType);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String string = EntityUtils.toString(httpResponse.getEntity());
		System.out.println(("EntityUtils.toString(httpResponse.getEntity()"));
		System.out.println(string);
		if(string != null){
			//JSONObject jsonObject = new JSONObject(string);
			//resp = new ObjectMapper().readValue(string,Map.class);
			System.out.println(string);
		}
		int statusCodeList = httpResponse.getStatusLine().getStatusCode();
        resp.put("key",Integer.toString(statusCodeList));
        return resp;
	}
	//"https://devws.aarcorp.com/adfbc-b2b-aarive/rest/r1/PoolOrderCount");
	// if api = pool order count ,name = "executeCount"
	public static Map postCalls(URL server,String api) throws ClientProtocolException, IOException, JSONException{
		Map<String,String> resp= new HashMap();
		HttpPost post = new HttpPost(new URL(server,api).toString());
		String name = "executeCount";
		
		Map<String,String> mm = new HashMap<>();
		mm.put("countTypeVar", "ALL");
		mm.put("email", "Alex.Walworth@aarcorp.com");
		Map<String,Object> m = new HashMap<>();
		m.put("name", "executeCount");
		m.put("parameters", mm);
		System.out.println(m);
		
		StringEntity postEntity = new StringEntity(
				"{\"name\": \"executeCount\",\"parameters\": [{\"countTypeVar\" : \"ALL\"},{\"email\" : \"Alex.Walworth@aarcorp.com\"}]}");

		post.setEntity(postEntity);
		post.setHeader("Content-Type", contentType);
		post.setHeader("Authorization", user);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(post);
		String string = EntityUtils.toString(httpResponse.getEntity());
		System.out.println(("EntityUtils.toString(httpResponse.getEntity()"));
		System.out.println(string);
		if(string != null){
			//JSONObject jsonObject = new JSONObject(string);
			resp = new ObjectMapper().readValue(string,Map.class);
			
		}
		int statusCodeList = httpResponse.getStatusLine().getStatusCode();
        resp.put("key",Integer.toString(statusCodeList));
        return resp;
	}

}
