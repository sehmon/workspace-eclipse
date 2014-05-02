package com.sehmon.milestracker;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MileListAdapter extends ArrayAdapter<Mile> {
	Context context;
	
	public MileListAdapter(Context context, int convertView, ArrayList<Mile> objects) {
		super(context, convertView, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Mile mile = getItem(position);
		
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.mile_list_item, null);
		}
		
		TextView dateTextView = (TextView)convertView.findViewById(R.id.tvDate);
		TextView lengthTextView = (TextView)convertView.findViewById(R.id.tvLength);
		TextView descriptionTextView = (TextView)convertView.findViewById(R.id.tvDescription);
		TextView timeTextView = (TextView)convertView.findViewById(R.id.tvTime);
		TextView typeTextView = (TextView)convertView.findViewById(R.id.tvType);
		
		String s = mile.getDate().toString();
		dateTextView.setText(s);
		lengthTextView.setText(String.valueOf(mile.getLength()));
		descriptionTextView.setText(mile.getDescription());
		
		timeTextView.setText(String.valueOf(mile.getTime()));
		typeTextView.setText(mile.getType());
		

	return convertView;
	}
}
