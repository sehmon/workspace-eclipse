package com.sehmon.foodscanner;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ScanActivity extends Activity{
	
	Button b1;
	TextView view1;
	TextView view2;
	FoodEssentialsClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		
		b1 = (Button)findViewById(R.id.button1);
		view1 = (TextView)findViewById(R.id.textView1);
		view2 = (TextView)findViewById(R.id.textView2);
		
		FoodEssentialsClient c = new FoodEssentialsClient();
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getData();
				
				/*if(v.getId()==R.id.button1){
				//scan
				IntentIntegrator scanIntegrator = new IntentIntegrator(this);
				scanIntegrator.initiateScan();
				} */
				
			}
		}
		); 
		
		

		

	}
	
	/*public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	        	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	        	String scanContent = scanningResult.getContents();
	        	String scanFormat = scanningResult.getFormatName();
        
	            view1.setText("FORMAT: " + scanFormat);
	            view2.setText("CONTENT: " + scanContent);
	        } else {
	        	Toast toast = Toast.makeText(getApplicationContext(), 
	        	        "No scan data received!", Toast.LENGTH_SHORT);
	        	    toast.show();
	        }
	    } else {
	    	Toast toast = Toast.makeText(getApplicationContext(), 
        	        "Wrong Code", Toast.LENGTH_SHORT);
        	    toast.show();
	    }
	} */

	
	private void getData() {
		
		client = new FoodEssentialsClient();
		
		client.getFoodData("028400071932", new JsonHttpResponseHandler(){
			
			
			@Override
			public void onSuccess(int code, JSONObject body){
				Toast.makeText(getApplicationContext(), "Getting Data", Toast.LENGTH_SHORT).show();
				JSONObject food = null;
				try{
					food = body.getJSONObject("product");
					
					b1.setText(food.getString("brand"));
					
					Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
					
				} catch(Exception e){
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(java.lang.Throwable e, org.json.JSONObject errorResponse){
				b1.setText("No");
				
			}
		});
		
		
		
	}
	

}
