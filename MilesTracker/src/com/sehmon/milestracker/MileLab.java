package com.sehmon.milestracker;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class MileLab {
	
	private static final String TAG = "MileLab";
	private static final String FILENAME = "miles.json";
    
    private ArrayList<Mile> mMiles;
    private MileJSONSerializer mSerializer;
    
    private static MileLab sMileLab;
    private Context mAppContext;
    
    
    private MileLab(Context appContext){
        mMiles = new ArrayList<Mile>();
       mAppContext = appContext;
    }
    
    public static MileLab get(Context c){
    	if(sMileLab == null){
    		sMileLab = new MileLab(c.getApplicationContext());
    	}
    	
    	return sMileLab;
    }
    
    public ArrayList<Mile> getMiles(){
    	return mMiles;
    }
    
    
    public double getTotalMileCount(){
    	double total = 0;
    	for(Mile m: mMiles){
    		total += m.getLength();
    	}
    	
    	return total;
    }
    
    public double getMileCountType(String searchString){
    	double total = 0;
    	for(Mile m: mMiles){
    		if(m.getType() == searchString){
    			total += m.getLength();
    		}
    	}
    	
    	return total;
    	
    }
    
    public boolean saveMiles(){
    	try {
    		mSerializer.saveMiles(mMiles);
    		Log.d(TAG, "crimes saved to file");
    		return true;
    	} catch (Exception e){
    		Log.e(TAG, "Error saving Crimes", e);
    		return false;
    	}
    }
    
    
    
}
