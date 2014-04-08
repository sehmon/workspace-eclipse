package com.example.android.rssfeed;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
	public static final String EXTRA_URL = "url";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		//Checks if activity has been switched to landscape mode
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			finish();
			return;
		}
		setContentView(R.layout.activity_detail);
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			String s = extras.getString(EXTRA_URL);
			TextView view = (TextView) findViewById(R.id.detailsText);
			view.setText(s);
		}
	}
	

}
