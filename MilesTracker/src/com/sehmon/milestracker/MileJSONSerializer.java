package com.sehmon.milestracker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

public class MileJSONSerializer {
	
	private Context mContext;
	private String mFilename;
	
	public MileJSONSerializer(Context c, String f){
		mContext = c;
		mFilename = f;
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
