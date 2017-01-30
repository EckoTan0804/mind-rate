package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;

public class AnswerQuestionnaireActivity extends BaseActivity implements View.OnClickListener {

    private final static int NEXT = 1;
    private final static int SUBMIT = 2;

    private int nextOrSubmit;

    private Questionnaire questionnaire;

    // ==================== view components =====================
    private TextView tv_questionnaireID;
    private Button btn_nextOrSubmit;

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

        // ================= Button back ========================================
        Button btn_back = (Button) findViewById(R.id.title_back);
        btn_back.setOnClickListener(this);

        // ======================================================================

        // ================ Button nextOrSubmit =================================
        btn_nextOrSubmit = (Button) findViewById(R.id.next_or_submit);
        setButtonAsNext();
        btn_nextOrSubmit.setOnClickListener(this);
        // ======================================================================

    }

    private void setButtonAsNext() {
        this.nextOrSubmit = NEXT;
        this.btn_nextOrSubmit.setText("Next");
    }

    private void setButtonSubmit() {
        this.nextOrSubmit = SUBMIT;
        this.btn_nextOrSubmit.setText("Submit");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.title_back:
                onBackPressed();
                break;
            case R.id.next_or_submit:
                if (this.nextOrSubmit == NEXT) {
                    // TODO: jump to next question
                } else if (this.nextOrSubmit == SUBMIT) {
                    // TODO: submit answer
                }
                break;
            default:

                break;
        }
    }
}
