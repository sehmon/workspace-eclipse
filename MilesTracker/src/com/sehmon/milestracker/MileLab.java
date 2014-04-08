package com.sehmon.milestracker;

import java.util.ArrayList;

import android.content.Context;

public class MileLab {
    
    private ArrayList<Mile> mMiles;
    
    private static MileLab sMileLab;
    
    
    
    private MileLab(Context appContext){
        mMiles = new ArrayList<Mile>();
        
        for(int i = 0; i < 5; i++){
        	Mile m = new Mile();
        	m.setLength(1);
        	mMiles.add(m);         
        }
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
    
    
    
}
