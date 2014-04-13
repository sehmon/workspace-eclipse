package com.sehmon.weatherapp;

import org.scribe.builder.api.Api;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class WeatherGrabber extends OAuthBaseClient {
	
	public WeatherGrabber(Context c, Class<? extends Api> apiClass,
			String consumerUrl, String consumerKey, String consumerSecret,
			String callbackUrl) {
		super(c, apiClass, consumerUrl, consumerKey, consumerSecret, callbackUrl);
		// TODO Auto-generated constructor stub
		
	}

	public static final String s = "hi";
	
	public void search(String term, String location, AsyncHttpResponseHandler handler) {
        // http://api.yelp.com/v2/search?term=food&location=San+Francisco
        String apiUrl = getApiUrl("search");
        RequestParams params = new RequestParams();
        params.put("term", term);
        params.put("location", location);
        client.get(apiUrl, params, handler);
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.google.com", new 
        		AsyncHttpResponseHandler(){
        	@Override
        	public void onSuccess(String response){
        		System.out.println(response);
        	}
        });
    }
	
	
	

}

