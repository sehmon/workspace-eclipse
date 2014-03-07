
package com.sehmon.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class TipCalc extends Activity {
    
    //The saved variables on exit or state change
    private static final String TOTAL_BILL = "TOTAL_BILL";
    private static final String CURRENT_TIP = "CURRENT_TIP";
    private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";
    
    // The variables used in the functions
    private double billBeforeTip;
    private double tipAmount;
    private double finalBill;
    
    //The edittext fields used to parse information
    EditText billBeforeTipET;
    EditText tipAmountET;
    EditText finalBillET;
    
    //The seekbar that updates the tip amount
    SeekBar tipSeekBar;
    
    private int[] checklistValues = new int[12];
    
    CheckBox friendlyCheckBox;
    CheckBox specialsCheckBox;
    CheckBox opinionCheckBox;
    
    RadioGroup availableRadioGroup;
    RadioButton availableBadRadio;
    RadioButton availableOKRadio;
    RadioButton availableGoodRadio;
    
    Spinner problemsSpinner;
    
    Button startChronometerButton;
    Button pauseChronometerButton;
    Button resetChronometerButton;
    
    Chronometer timeWaitingChronometer;
    
    long secondsYouWaited = 0;
    
    TextView timeWaitingTextView;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        //Sets the active activity to the tip calc?
        setContentView(R.layout.activity_tip_calc);
        
        //Checks if any variables are saved from a previous run of the app
        if(savedInstanceState == null){
            
            //Default values 
            billBeforeTip = 0.0;
            tipAmount = 0.15;
            finalBill = 0.0;
            
        } else {
            
            //Sets the variables to the previous saved instances
            billBeforeTip = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
            tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
            finalBill = savedInstanceState.getDouble(TOTAL_BILL);
            
        }
        
        //Sets the EditText variables to their corresponding fields by using their ID
        billBeforeTipET = (EditText) findViewById(R.id.billEditText);
        tipAmountET = (EditText) findViewById(R.id.tipEditText);
        finalBillET = (EditText) findViewById(R.id.finalBillEditText);
        
        //sets the SeekBar variable to its corresponding seekbar usind its ID
        tipSeekBar = (SeekBar) findViewById(R.id.seekBar);
        
        //runs the method on the seekbar
        tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
        
        //when the listener hears change it runs the function bellow
        billBeforeTipET.addTextChangedListener(billBeforeTipListener);
        
        friendlyCheckBox = (CheckBox) findViewById(R.id.friendlyCheckBox);
        specialsCheckBox = (CheckBox) findViewById(R.id.specialsCheckBox);
        opinionCheckBox = (CheckBox) findViewById(R.id.opinionCheckBox);
        
        setUpIntroCheckBoxes();
        
        availableRadioGroup = (RadioGroup) findViewById(R.id.availableRadioGroup);
        RadioButton availableBadRadio = (RadioButton) findViewById(R.id.availableBadRadio);
        RadioButton availableOKRadio = (RadioButton) findViewById(R.id.availableOKRadio);
        RadioButton availableGoodRadio = (RadioButton) findViewById(R.id.availableGoodRadio);
        
        addChangeListenerToRadios();
        
        problemsSpinner = (Spinner) findViewById(R.id.problemsSpinner);
        
        addItemSelectedListenerToSpinner();
        
        startChronometerButton = (Button) findViewById(R.id.startChronometerButton);
        pauseChronometerButton = (Button) findViewById(R.id.pauseChronometerButton);
        resetChronometerButton = (Button) findViewById(R.id.resetChronometerButton);
        
        setButtonOnClickListener();
        
        timeWaitingChronometer = (Chronometer) findViewById(R.id.timeWaitingChronometer);
        
        timeWaitingTextView = (TextView) findViewById(R.id.timeWaitingTextView);
        
    }
    
    //sets billBeforeTipListener to a text watcher
    private TextWatcher billBeforeTipListener = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub
            
        }

        @Override
        //The function that updates the field Total 
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            try {
                
                //Tries to parse a double out of the field billBeforeTip
                billBeforeTip = Double.parseDouble(s.toString());
            
            }
            
            catch(NumberFormatException e){
                //If not number format sets bill to 0
                billBeforeTip = 0.0;
            }
            
            //updates all other fields
            updateTipAndFinalBill();
        }

       
        
    };
    
    //function that updates other fields
    private void updateTipAndFinalBill(){
        
        //Gets the tip amount from the tipAmountET EditText Field
        double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
        
        //Creates a variable that equals the final bill
        double finalBill = billBeforeTip + (billBeforeTip * tipAmount);
        
        //Sets the final bill EditText to the finalBill variable 
        finalBillET.setText(String.format("%.02f", finalBill));
        
    }
    
    //on instance state change, saved field input as app variables
    protected void onSaveInstanceState(Bundle outState){
        
        super.onSaveInstanceState(outState);
        
        outState.putDouble(TOTAL_BILL, finalBill);
        outState.putDouble(CURRENT_TIP, tipAmount);
        outState.putDouble(BILL_WITHOUT_TIP, billBeforeTip);
        
    }
    
    private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener(){

        @Override
        //When progress bar is changed the tip amount also changes
        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            // TODO Auto-generated method stub
            tipAmount = (tipSeekBar.getProgress()) * .01;
            
            tipAmountET.setText(String.format("%.02f", tipAmount));
            
            updateTipAndFinalBill();
            
        }

        @Override
        public void onStartTrackingTouch(SeekBar arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onStopTrackingTouch(SeekBar arg0) {
            // TODO Auto-generated method stub
            
        }
        
        
    };
    
private void setUpIntroCheckBoxes(){
        
        // Add ChangeListener to the friendlyCheckBox
        
        friendlyCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        
                // Use java ternary operator to set the right values for
                // each item on the waitress check box checklist
                        
                checklistValues[0] = (friendlyCheckBox.isChecked())?4:0;
                
                // Calculate tip using the waitress checklist options
                
                setTipFromWaitressChecklist(); 
                        
                // Update all the other EditTexts
                        
                updateTipAndFinalBill();
                        
            }
                    
        });
        
        // Add ChangeListener to the specialsCheckBox
        
        specialsCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        
                // Use java ternary operator to set the right values for
                // each item on the waitress check box checklist
                        
                checklistValues[1] = (specialsCheckBox.isChecked())?1:0;
                
                // Calculate tip using the waitress checklist options
                
                setTipFromWaitressChecklist(); 
                        
                // Update all the other EditTexts
                        
                updateTipAndFinalBill();
                        
            }
                    
        });     
        
        // Add ChangeListener to the opinionCheckBox
        
        opinionCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                        
                // Use java ternary operator to set the right values for
                // each item on the waitress check box checklist
                        
                checklistValues[2] = (opinionCheckBox.isChecked())?2:0;
                
                // Calculate tip using the waitress checklist options
                
                setTipFromWaitressChecklist(); 
                        
                // Update all the other EditTexts
                        
                updateTipAndFinalBill();
                        
            }
                    
        });             
        
    }
    
    // Calculate tip using the waitress checklist options
    
    private void setTipFromWaitressChecklist(){
        
        int checklistTotal = 0;
        
        // Cycle through all the checklist values to calculate
        // a total amount based on waitress performance
        
        for(int item : checklistValues){
            
            checklistTotal += item;
            
        }
        
        // Set tipAmountET 
        
        tipAmountET.setText(String.format("%.02f", checklistTotal * .01));
        
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calc, menu);
        return true;
    }

}
