package com.sehmon.simpletodo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {
	
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView tasksListView;
	
	
	EditText newTask;
	Button addNewTask;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		//This prevents the keyboard from automatically opening on activity start
		getWindow().setSoftInputMode(
			    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		tasksListView = (ListView)findViewById(R.id.listViewItems);
		items = new ArrayList<String>();
		
		//This sets the Adapter to this Activity, and the items
		//in the list to a default list item layout
		itemsAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, items);
		
		//Sets the adapter to work with the listView
		tasksListView.setAdapter(itemsAdapter);
		
		//Gives the list a listener that checks if any of the 
		//Itens are long clicked
		tasksListView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				// TODO Auto-generated method stub
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				return true;
			}
			
		});
		
		for(int i = 1; i < 3; i++){
			items.add("Item #" + i);
		}
		
		newTask = (EditText)findViewById(R.id.editTextNewItem);
		
		addNewTask = (Button)findViewById(R.id.buttonAddItem);
		addNewTask.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				items.add(newTask.getText().toString());
				newTask.setText("");
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

}
