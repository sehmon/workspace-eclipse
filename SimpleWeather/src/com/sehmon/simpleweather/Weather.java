package com.sehmon.simpleweather;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
	private String mLocation;
	private String mTime;
	private String mWeather;
	private String mTemperature;
	private String mHumidity;
	private String mIcon_Url;
	
	
	public String getmLocation() {
		return mLocation;
	}
	public String getmTime() {
		return mTime;
	}
	public String getmWeather() {
		return mWeather;
	}
	public String getmTemperature() {
		return mTemperature;
	}
	public String getmHumidity() {
		return mHumidity;
	}
	public String getmIcon_Url() {
		return mIcon_Url;
	}
	
	public static Weather fromJson(JSONObject jsonObject){
		Weather w = new Weather();
		try {
			w.mLocation = jsonObject.getJSONObject("display_location").getString("full");
			w.mTime = jsonObject.getString("observation_time");
			w.mWeather = jsonObject.getString("weather");
			w.mTemperature = jsonObject.getString("temperature_string");
			w.mHumidity = jsonObject.getString("relative_humidity");
			w.mIcon_Url = jsonObject.getString("icon_url");
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return w;
	}
	
	

}
