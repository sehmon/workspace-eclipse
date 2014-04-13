package com.sehmon.whatamieating;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreFragment extends Fragment {
	
	NutrientProvider provider;
	TextView tv1;

	public static Fragment newInstance() {
		return new ScoreFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_score,
				container, false);
		tv1 = (TextView)rootView.findViewById(R.id.scoreTextView);
		
		
		return rootView;
	}
	
	public ScoreFragment(){
		
	}
}
