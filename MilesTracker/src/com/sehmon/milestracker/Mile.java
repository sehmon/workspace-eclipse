package com.sehmon.milestracker;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Mile {
	
	private static final String JSON_ID = "id";
	private static final String JSON_LENGTH = "length";
	private static final String JSON_TYPE = "type";
	private static final String JSON_TIME = "time";
	private static final String JSON_DATE = "date";
	private static final String JSON_DESCRIPTION = "description";
    
    private UUID mId;
    private double mLength;
    //Length is in seconds
    private String mType;
    private double mTime;
    private Date mDate;
    private String mDescription;
    
    public Mile() {
        
        mLength = 0;
        mType = "Default";
        mDate = new Date();
    }
    
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public double getLength() {
        return mLength;
    }
    public void setLength(double length) {
        mLength = length;
    }
    public String getType() {
        return mType;
    }
    public void setType(String type) {
        mType = type;
    }
    public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }

	public double getTime() {
		return mTime;
	}

	public void setTime(double mTime) {
		this.mTime = mTime;
	}

	public JSONObject toJSON() throws JSONException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TYPE, mType);
		json.put(JSON_LENGTH, mLength);
		json.put(JSON_TIME, mTime);
		json.put(JSON_DATE, mDate.toString());
		json.put(JSON_DESCRIPTION, mDescription);
		
		return json;
	}
    
    
}

