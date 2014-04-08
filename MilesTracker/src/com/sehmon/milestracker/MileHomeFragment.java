package com.sehmon.milestracker;

import java.util.Date;
import java.util.UUID;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class MileHomeFragment extends Fragment {
	
	//This is the int needed to use startActivityForResult()
	private static final int GET_NEW_MILE = 1;
	
	private double mTotalNeeded = 50;
	private double mTotalRan;
	private double mTotalToGo;
	
	TextView mMilesNeededTextView;
	TextView mMilesRanTextView;
	TextView mMilesToGoTextView;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.app_name);
		
		mTotalRan = MileLab.get(getActivity()).getTotalMileCount();
		
		//If you want to inflate a menu, THIS IS NEEDED!!!!!!!
		setHasOptionsMenu(true);
				
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	            /*This is where you would save data to keep on rotation change  */
	}
	
	
	public MileHomeFragment newInstance(int title, String message){
		return new MileHomeFragment();
		}
	
	//Inflates the menu for the action bar
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.miles_main, menu);
	}

	
	//This uses a switch to check which action bar button you press
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_new_mile:
	        	getNewMile();
	        	break;  
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return false;
	   
	}
	
	//The function called in the switch statement to get a new mile
	private void getNewMile(){
		Intent getMileIntent = new Intent(getActivity().getApplicationContext(), MileNewActivity.class);
		startActivityForResult(getMileIntent, GET_NEW_MILE);
	}
	
	
	//TODO set the rest of the mile attributes to the ones in the new_mile_fragment
	//This method deals with the addition of a new Mile
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == GET_NEW_MILE){
			if(resultCode == 1){
			try{
				Mile m = new Mile();
				
	        
	          m.setDate((Date)data.getSerializableExtra("date"));
	          
	          if(m.getId() == null){
		          m.setId((UUID) data.getSerializableExtra("id"));  
	          }
          
	          m.setLength(data.getDoubleExtra("length",0));
	          
	          m.setTime(data.getDoubleExtra("time", 0));
	          
	          m.setDescription(data.getStringExtra("description"));
	          
	          m.setType(data.getStringExtra("type"));
	          
	          MileLab.get(getActivity()).getMiles().add(m); 
	          
	        } catch(Exception e){
	          System.out.println(e.getMessage());
	        }
	              
			} if(resultCode == 2){
				
			}
			
			MileHomeFragment fragment = new MileHomeFragment();
	        getFragmentManager().beginTransaction().replace(R.id.homeFragmentContainer, fragment).commit();
		} 
			
	}
	


	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mile_home, parent, false);
		
		mMilesNeededTextView = (TextView)v.findViewById(R.id.mile_needed_number);
		mMilesNeededTextView.setText(String.valueOf(this.mTotalNeeded));
		
		mTotalRan = MileLab.get(getActivity()).getTotalMileCount();
		mMilesRanTextView = (TextView)v.findViewById(R.id.mile_ran_number);
		mMilesRanTextView.setText(String.valueOf(String.valueOf(mTotalRan)));
		
		mTotalToGo = mTotalNeeded-mTotalRan;
		mMilesToGoTextView = (TextView)v.findViewById(R.id.mile_to_go_number);
		mMilesToGoTextView.setText(String.valueOf(mTotalToGo));
		
		v.invalidate();
		
		return v;
		
		
	}
	

	
}
