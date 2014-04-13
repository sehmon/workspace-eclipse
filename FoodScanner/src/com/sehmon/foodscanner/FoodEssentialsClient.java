package com.sehmon.foodscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FoodEssentialsClient {
	
	private final String sid = "4ed84b9c-7ae7-44b4-ae63-d17a0bd6d673";	
	private AsyncHttpClient client;
	
	private final String INGREDIENT_GET_URL = "http://api.foodessentials.com/createsession?uid=demoUID_01&devid=demoDev_01&appid=demoApp_01&f=json&api_key=";
	private static final String API_KEY = "m5pkqfejmtvxsw3en5rnjagu";

	public FoodEssentialsClient() {
		AsyncTask<String, String, String> async = new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				HttpClient client = new DefaultHttpClient();
				String path = arg0[0];
				Log.e("MALTZ", "path is " + path);
				HttpGet getReq = new HttpGet("http://api.foodessentials.com/productscore?u=028400071932&sid=d67a2602-46a2-489a-9483-c7a43a924f4b&f=json&api_key=m5pkqfejmtvxsw3en5rnjagu");
				String responseString = "";
				try {
					HttpResponse resp = client.execute(getReq);
					BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
					// read the response
					String temp = "";
					while ((temp = reader.readLine()) != null) {
						responseString += temp;
					}
					Log.e("MALTZ", "response is " + responseString);
					return responseString;
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return responseString;
			}
			
			@Override
			protected void onPostExecute(String res) {
				JSONObject jsonObj = new JSONObject(res);
			}
		};
		async.execute("blah");
	}
	
	public void getFoodData(String barcode, JsonHttpResponseHandler handler){
		String url = ("http://api.foodessentials.com/productscore?u=028400071932&sid=d67a2602-46a2-489a-9483-c7a43a924f4b&f=json&api_key=m5pkqfejmtvxsw3en5rnjagu");
		RequestParams params = new RequestParams("apikey", API_KEY);
		client.get(url, params, handler);
	}
	

}
