package com.sehmon.foodscanner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

public class CopyOfFoodEssentialsClient {
	
	public CopyOfFoodEssentialsClient(){
		
	}
	
	String s;
	
	public void httpGet(){
	
	DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
	HttpPost httppost = new HttpPost("http://api.foodessentials.com/productscore?u=028400071932&sid=d67a2602-46a2-489a-9483-c7a43a924f4b&f=json&api_key=m5pkqfejmtvxsw3en5rnjagu");
	// Depends on your web service
	
	httppost.setHeader("Content-type", "application/json");
	InputStream inputStream = null;
	String result = null;
	try {
	    HttpResponse response = httpclient.execute(httppost);           
	    HttpEntity entity = response.getEntity();

	    inputStream = entity.getContent();
	    // json is UTF-8 by default
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    while ((line = reader.readLine()) != null)
	    {
	        sb.append(line + "\n");
	    }
	    result = sb.toString();
	} catch (Exception e) { 
	    // Oops
	}
	finally {
	    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
	
	
	try {
		JSONObject jObject = new JSONObject(result);
		s = jObject.getString("upc");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	

}
