package com.sehmon.milestracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MileListFragment extends Fragment {

	MileListAdapter adapter;	
	ListView lvList;
		public static Fragment newInstance(){
			return new MileListFragment();
		}
		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, 
				Bundle savedInstanceState){
			
			View rootView = inflater.inflate(R.layout.fragment_mile_list, null);
			adapter = new MileListAdapter(getActivity(), R.id.list_item, MileLab.get(getActivity()).getMiles());
			
			lvList = (ListView)rootView.findViewById(R.id.lvMiles);
			lvList.setAdapter(adapter);
			
			return rootView;
		}
		
}

