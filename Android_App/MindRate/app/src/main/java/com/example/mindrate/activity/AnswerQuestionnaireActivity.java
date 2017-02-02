package com.example.mindrate.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.QuestionnaireAnswer;

public class AnswerQuestionnaireActivity extends BaseActivity implements View.OnClickListener {

    private final static int NEXT = 1;
    private final static int SUBMIT = 2;

    private int nextOrSubmit;
    private String nextQuestionID;
    private Question currentQuestion;
    private QuestionnaireAnswer questionnaireAnswer;

    private Questionnaire questionnaire;

    // ==================== view components =====================
    private TextView tv_questionnaireID;
    private FloatingActionButton btn_nextOrSubmit;
    private LinearLayout ll_displayAnswerOption;
    private TextView tv_question;
    private LinearLayout ll_activityAnswerQuestionnaire;

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
        this.questionnaire = intent.getParcelableExtra("questionnaire");
        this.questionnaireAnswer = new QuestionnaireAnswer(this.questionnaire.getQuestionnaireID());
    }

    private void initView() {

        ll_displayAnswerOption = (LinearLayout) findViewById(R.id.activity_answer_questionnaire);

        tv_questionnaireID = (TextView) findViewById(R.id.title_questionnaireID);
        tv_questionnaireID.setText(questionnaire.getQuestionnaireID());

        // ================= Button back ========================================
        Button btn_back = (Button) findViewById(R.id.title_back);
        btn_back.setOnClickListener(this);

        // ======================================================================

        // ================ Button nextOrSubmit =================================
        btn_nextOrSubmit = (FloatingActionButton) findViewById(R.id.next_or_submit);
        setButtonAsNext();
        btn_nextOrSubmit.setOnClickListener(this);
        // ======================================================================

        // ================ TextView question ===================================
        tv_question = (TextView) findViewById(R.id.question);
        // ======================================================================

        // ================ LinearLayout displayAnswerOption ====================
        ll_displayAnswerOption = (LinearLayout) findViewById(R.id.display_answer_option);
        // ======================================================================
        this.currentQuestion = this.questionnaire.getQuestionList().get(0);
        this.currentQuestion.inflateView(tv_question, this,
                                         ll_displayAnswerOption, null);


    }

    private void setButtonAsNext() {
        this.nextOrSubmit = NEXT;
        this.btn_nextOrSubmit.setImageResource(R.mipmap.ic_arrow_forward);
    }

    private void setButtonAsSubmit() {
        this.nextOrSubmit = SUBMIT;
        this.btn_nextOrSubmit.setImageResource(R.mipmap.ic_done);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                onBackPressed();
                break;
            case R.id.next_or_submit:

                if (this.nextOrSubmit == NEXT) {

                    // 0. add this question's answer to questionnaireAnswer
                    recordAnswer();

                    // 1. remove last questions's view
                    ll_displayAnswerOption.removeAllViews();

                    // 2. determine whether nextQuestionID is default or specified
                    if (currentQuestion.getQuestionType().getNextQuestionID() != null) {
                        nextQuestionID = currentQuestion.getQuestionType().getNextQuestionID();
                    } else {
                        nextQuestionID = this.questionnaire.defaultNextQuestionID(currentQuestion);
                    }

                    // 3. get next question
                    Question nextQuestion = this.questionnaire.getQuestion(nextQuestionID);

                    // 4. next question inflates its view
                    nextQuestion.inflateView(tv_question, this, ll_displayAnswerOption, null);

                    // 5. determine whether next question is the last question
                    if (this.questionnaire.isLastQuestion(nextQuestion)) {
                        setButtonAsSubmit();
                    }

                    // 6. Iteration: set nextQuestion as currentQuestion
                    currentQuestion = nextQuestion;

                } else if (this.nextOrSubmit == SUBMIT) {

                    // record last question's answer
                    recordAnswer();

                    //                    StringBuilder builder = new StringBuilder();
                    //                    for (QuestionAnswer answer : this.questionnaireAnswer
                    // .getQuestionAnswerList()) {
                    //                        builder.append(answer.getQuestionID() + ":" +
                    // answer.getAnswerContent() +
                    //                                "\n");
                    //                    }
                    //                    String result = builder.toString();
                    //                    AlertDialog.Builder dialog = new AlertDialog.Builder
                    //                            (AnswerQuestionnaireActivity.this);
                    //                    dialog.setMessage(result);
                    //                    dialog.show();

                    // alertDialog
                    AlertDialog.Builder dialog = new AlertDialog.Builder
                            (AnswerQuestionnaireActivity.this);
                    dialog.setTitle("Submit");
                    dialog.setMessage("Do you want to submit your answer?");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            // 0. submit answer

                            // 1. save answer locally

                            // 2. upload answer to server

                            // 3. set this questionnaire as isAnswered
                            questionnaire.setAnswered(true);

                            // 4. back to OverviewActivity
                            Intent intent = new Intent(AnswerQuestionnaireActivity.this,
                                                       OverviewActivity.class);
                            intent.putExtra("answered questionnaire ID", questionnaire
                                    .getQuestionnaireID());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });
                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();

                }
                break;
            default:

                break;
        }
    }

    private void recordAnswer() {
        this.questionnaireAnswer.getQuestionAnswerList().add(this.currentQuestion
                                                                     .getQuestionType()
                                                                     .getQuestionAnswer());
    }


}
