package com.sehmon.parsetododemo;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class ParseApplication extends Application {
	
	@Override
	public void onCreate(){
		super.onCreate();
		
		Parse.initialize(this, "Ss8EZt8NU0lMh4CfuRYhSzaXsu2HoQFpTynPnf7I", "EByy1kd6sezTVOEnNfGJEZkepqAIE09x06EulUT6");
		
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
		
		PushService.setDefaultPushCallback(this, TodoItemsActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
