package com.sehmon.milestracker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	
	public static final String EXTRA_DATE = 
            "com.sehmon.miletracker.date";
    
    private Date mDate;
    
    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        
        return fragment;
    }

       @Override
       public Dialog onCreateDialog(Bundle savedInstanceState){
           mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
           
           //Create a calendar to get the year, month, and day
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(mDate);
           int year = calendar.get(Calendar.YEAR);
           int month = calendar.get(Calendar.MONTH);
           int day = calendar.get(Calendar.DAY_OF_MONTH);
           
           View v = getActivity().getLayoutInflater()
                   .inflate(R.layout.new_mile_dialog_date, null);
           
           DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
           datePicker.init(year, month, day, new OnDateChangedListener(){
               public void onDateChanged(DatePicker view, int year, int month, int day){
                   mDate = new GregorianCalendar(year, month, day).getTime();
                   
                   getArguments().putSerializable(EXTRA_DATE, mDate);
               }
           });
           
           
           
           return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_dialog)
                .setPositiveButton(android.R.string.ok, null)
                .create();
       }

}
