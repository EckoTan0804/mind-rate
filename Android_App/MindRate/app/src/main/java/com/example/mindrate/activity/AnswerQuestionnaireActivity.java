package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;

public class AnswerQuestionnaireActivity extends BaseActivity {

    private Questionnaire questionnaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questionnaire);
    }

    private void initFromIntent() {
        Intent intent = getIntent();

    }
}
