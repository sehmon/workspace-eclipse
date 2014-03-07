package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
    
    public static final String EXTRA_ANSWER_IS_TRUE = 
            "com.bignerdranch.android.geoquiz.answer_is_true";
    
    public static final String EXTRA_ANSWER_SHOWN = 
            "com.bignerdranch.android.geoquiz.answer_is_shown";
    
    private boolean mAnswerIsTrue;
    private Button mShowAnswer;
    private TextView mAnswerTextView;
    private TextView mApiField;
    
    String api = "Api Level " + Build.VERSION.SDK_INT;
    
    //This method is used to attach an Extra to the intent to tell is the 
    // Answer is shown or not
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // Created
        setAnswerShownResult(false);
        
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        
        mApiField = (TextView)findViewById(R.id.apiField);
        
        mApiField.setText(api);
        
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

}
