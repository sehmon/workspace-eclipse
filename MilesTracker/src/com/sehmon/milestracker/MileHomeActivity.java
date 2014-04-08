package com.sehmon.milestracker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class MileHomeActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_miles_main);
		
		FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.homeFragmentContainer);
        
        if(fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
            .add(R.id.homeFragmentContainer, fragment)
            .commit();
        } else {
        	fm.beginTransaction()
        	.replace(R.id.homeFragmentContainer, fragment)
        	.commit();
        } 
    }
	
	protected Fragment createFragment() {
		return new MileHomeFragment();
	}
	


}
