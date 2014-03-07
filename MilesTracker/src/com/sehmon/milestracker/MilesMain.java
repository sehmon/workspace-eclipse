
package com.sehmon.milestracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;

public class MilesMain extends Activity {

    private ArrayList<Mile> miles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.miles_main, menu);
        return true;
    }

}
