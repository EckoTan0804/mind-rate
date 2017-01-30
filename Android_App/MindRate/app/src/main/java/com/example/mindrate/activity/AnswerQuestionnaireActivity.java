package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        tv_questionnaireID = (TextView) findViewById(R.id.title_questionnaireID);
        tv_questionnaireID.setText(questionnaire.getQuestionnaireID());

        Button btn_back = (Button) findViewById(R.id.title_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        setResult(Activity.RESULT_OK);
//        finish();
//    }
}
