
package com.bignerdranch.android.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    
    //The tag created for debugging
    //private static final String TAG = "QuizActivity";
    
    //The name of the saved instance when the device state changes
    private static final String KEY_INDEX = "index";
    
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    
    private TextView mQuestionTextView;
    
    private Button mCheatButton;
    
    //The Array of Questions that the user is tested on 
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true), 
            new TrueFalse(R.string.question_mideast, false), 
            new TrueFalse(R.string.question_africa, false), 
            new TrueFalse(R.string.question_americas, true), 
            new TrueFalse(R.string.question_asia, true),
    };
    
    //The index of the question array
    private int mCurrentIndex = 0;
    
    //The boolean that determines the toast
    private boolean mIsCheater;
    
    //Uses the index to refresh the question in the TextView
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data == null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    //This runs the button's true/false with the question's correct answer
    public void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        
        if(mIsCheater){
            messageResId = R.string.judgement_toast;
        } else {
              
            if(userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        
        }
        
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
       
    }
    
    
    //Putting @Override lets you override default methods
    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        
        //Makes sure the device wont implement an action bar if the device does'nt have one
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
        ActionBar actionBar = getActionBar();
        actionBar.setSubtitle("Bodies of Water");
        }
        
        //Links the variables to the widgets they associate with 
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        
        //Sets up the action for when the button is pressed using an anonymous class
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
               mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
               mIsCheater = false;
               updateQuestion();
               
                
            }
        });
        
        mPrevButton = (ImageButton)findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                try{
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    } catch(Exception e) {
                    mCurrentIndex = 0;
                    } finally {
               updateQuestion();
                    }
                
            }
        });
        
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,  answerIsTrue);
                startActivityForResult(i, 0);
                
            }
        });
        
        //If the saved instance has something in it, then load it
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }
   

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }
    
    @Override
    public void onStart(){
        super.onStart();
        //Log.d(TAG, "onStart() called");
    
    }
    
    @Override
    public void onPause(){
        super.onPause();
        //Log.d(TAG, "onPause() called");
    }
    
    @Override
    public void onStop(){
        super.onStop();
        //Log.d(TAG, "onStop() called");
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
        //Log.d(TAG, "onDestroy() called");
    }

}
