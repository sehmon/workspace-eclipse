package com.sehmon.milestracker;

import java.util.Date;
import java.util.UUID;

public class Mile {
    
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
    
    
}

