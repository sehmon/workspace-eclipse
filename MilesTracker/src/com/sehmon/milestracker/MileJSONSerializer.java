package com.sehmon.milestracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class MileJSONSerializer {
	
	private Context mContext;
	private String mFilename;
	
	public MileJSONSerializer(Context c, String f){
		mContext = c;
		mFilename = f;
	}
	
	public ArrayList<Mile> loadMiles() throws IOException, JSONException {
		ArrayList<Mile> miles = new ArrayList<Mile>();
		BufferedReader reader = null;
		
		try {
			//Open and read the file in a String Builder
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				//Line breaks are ommitted and irrelevant
				jsonString.append(line);
			}
			//Parse the JSON using JSONTokener
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
				.nextValue();
			//Build the array of miles from JSONObjects
			for(int i = 0; i < array.length(); i++){
				miles.add(new Mile(array.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			//Ignore, this happens when starting fresh
		} finally {
			if(reader != null)
				reader.close();		
		}
		return miles;
	}
	
	public void saveMiles(ArrayList<Mile> Miles) 
			throws JSONException, IOException {
		
		//Build the JSON Array
		JSONArray array = new JSONArray();
		for(Mile m:Miles){
			array.put(m.toJSON());
		}
		
		//Write File to Disk
		Writer writer = null;
		try {
			OutputStream out = mContext
					.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if(writer != null){
				writer.close();
			}
		}
	}

}
