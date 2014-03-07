package com.sehmon.milestracker;

import android.content.Context;

import java.util.ArrayList;

public class MileLab {
    
    private ArrayList<Mile> mMiles;
    
    private static MileLab sMileLab;
    private Context mAppContext;
    
    private MileLab(Context appContext){
        mAppContext = appContext;
        mMiles = new ArrayList<Mile>();
        for(int i = 0; i < 5; i++){
            Mile m = new Mile()
           
        }
    }
    
    
    
}
