package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;

public class AnswerQuestionnaireActivity extends BaseActivity {

    private Questionnaire questionnaire;

    // ==================== view components =====================
    private TextView tv_questionnaireID;

    // ==========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questionnaire);

        initFromIntent();
        initView();
    }

    private void initFromIntent() {
        Intent intent = getIntent();
        this.questionnaire =  intent.getParcelableExtra("questionnaire");
    }

    private void initView() {

        tv_questionnaireID = (TextView) findViewById(R.id.titl_questionnaireID);
        tv_questionnaireID.setText(questionnaire.getQuestionnaireID());
    }
}
