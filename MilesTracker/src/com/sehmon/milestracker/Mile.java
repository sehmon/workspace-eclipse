package com.sehmon.milestracker;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public abstract class Mile {
    
    private UUID mId;
    private int mLength;
    private String mType;
    private Date mDate;
    
    public Mile() {
        
        mId = UUID.randomUUID();
        mLength = 0;
        mType = "Default";
        mDate = Calendar.getInstance().getTime();
    }
    
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public int getLength() {
        return mLength;
    }
    public void setLength(int length) {
        mLength = length;
    }
    public String getType() {
        return mType;
    }
    public void setType(String type) {
        mType = type;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    
    
}

