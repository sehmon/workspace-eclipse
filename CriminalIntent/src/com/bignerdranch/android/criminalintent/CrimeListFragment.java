package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {
    
    public static final String TAG = "CrimeListFragment";
    
    private ArrayList<Crime> mCrimes;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Sets title of Fragment on Action Bar
        getActivity().setTitle(R.string.crimes_title);
        
        //Sets the array list to the CrimeLab
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
        
        
        //The setListAdapter(ListAdapter) method is a 
        //ListFragment convenience method that you can
        //use to set the adapter of the implicit ListView
        //managed by CrimeListFragment.
        
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Toast.makeText(getActivity(), c.getTitle() + " was pressed", Toast.LENGTH_SHORT).show();
        
        //CrimeActivity
        //This uses an intent to return the Crime Activity to the user
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }
    
    //On resume is the safest place to take action to update a fragments view
    @Override
    public void onResume(){
        super.onResume();
        
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
    
    private class CrimeAdapter extends ArrayAdapter<Crime> {
        
    
        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(), 0, crimes);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);                
            }
            
        Crime c = getItem(position);
        
        //Remember convertView
        TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
        titleTextView.setText(c.getTitle());
        
        TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
        dateTextView.setText(c.getDate().toString());
        
        CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
        solvedCheckBox.setChecked(c.isSolved());
        
        
        return convertView;
            
        }
    }   

}
