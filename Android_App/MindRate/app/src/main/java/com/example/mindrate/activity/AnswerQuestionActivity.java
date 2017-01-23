package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;

public class AnswerQuestionActivity extends AppCompatActivity {

    private TextView tv_questionText;

    private Proband proband;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        initFromIntent();
        initView();

//        tv_questionText.setText(Utility.createJSON(this.proband));
    }

    private void initFromIntent() {
        Intent intent = getIntent();
        String questionnaire = intent.getStringExtra("questionnaire_JSON");
        this.proband = (Proband) intent.getParcelableExtra("proband");
    }

    private void initView() {
//        tv_questionText = (TextView) findViewById(R.id.question_text);
    }
}
