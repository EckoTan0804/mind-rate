package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.mindrate.R;
import com.example.mindrate.gson.Birthday;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.util.Utility;

import java.util.Calendar;
import java.util.TimeZone;

public class LogInActivity extends AppCompatActivity {


    public static final String SERVER_ADDRESS = "129.13.170.45";

    // =================== proband ==========================
    private String studyID;
    private String probandID;
    private int year;
    private int month;
    private int day;
    private String gender;
    private String occupation;

    // =================== view component ===================
    private EditText edtTxt_studyID;
    private EditText edtTxt_probandID;
    private DatePicker dtPk_birthday;
    private RadioGroup rdog_chooseGender;
    //    private RadioButton rdoBtn_gender_male;
//    private RadioButton rdoBtn_gender_famale;
    private EditText edtTxt_occuptaion;
    private Button btn_probandLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ActivityManager.addActivity(this);
        initView();

    }

    private void initView() {


        // ==================== study ID ===============================
        edtTxt_studyID = (EditText) findViewById(R.id.study_id);
        // =============================================================

        // ==================== proband ID =============================
        edtTxt_probandID = (EditText) findViewById(R.id.proband_id);
        // =============================================================

        // ================= DatePicker ================================

        dtPk_birthday = (DatePicker) findViewById(R.id.choose_birthday);
        Calendar calender = Calendar.getInstance(TimeZone.getDefault());
        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DATE);
        dtPk_birthday.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int changedYear, int monthOfYear, int
                    dayOfMonth) {
                year = changedYear;
                month = monthOfYear + 1;
                day = dayOfMonth;
//                Toast.makeText(LogInActivity.this, year+ "." + month + "." + day, Toast
//                        .LENGTH_SHORT).show();
            }
        });

        // ============================================================

        // =============== gender =====================================
        rdog_chooseGender = (RadioGroup) findViewById(R.id.choose_gender);
//        rdoBtn_gender_male = (RadioButton) findViewById(R.id.male);
//        rdoBtn_gender_famale = (RadioButton) findViewById(R.id.female);

        // TODO: default checked
        rdog_chooseGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = "male";
//                        Toast.makeText(LogInActivity.this, gender, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.female:
                        gender = "female";
//                        Toast.makeText(LogInActivity.this, gender, Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        break;
                }
            }
        });

//        rdoBtn_gender_male.setOnCheckedChangeListener(this);
//        rdoBtn_gender_famale.setOnCheckedChangeListener(this);


        // ==============================================================================

        // ========================= occupation =========================================
        edtTxt_occuptaion = (EditText) findViewById(R.id.occupation);
        // ==============================================================================

        // ========================= log in =============================================

        btn_probandLogIn = (Button) findViewById(R.id.proband_log_in);

        btn_probandLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. TODO: create Proband instance
                studyID = edtTxt_studyID.getText().toString();
                probandID = edtTxt_probandID.getText().toString();
                occupation = edtTxt_occuptaion.getText().toString();
                Proband proband = new Proband(studyID, probandID, new Birthday(year, month, day),
                        gender, occupation);

                // 2. TODO: create probandJSON
                String probandJSON = Utility.createJSON(proband);

                // 3. TODO: upload probandJSON to server and download Questionnaires
//                String questionnaireJSON = null;
//                try {
//                    questionnaireJSON = HttpUtil.post(SERVER_ADDRESS, probandJSON);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//
//                }

                // 4. TODO: put proband & questionnaireJSON into inteng
                Intent intent = new Intent(LogInActivity.this, AnswerQuestionActivity.class);
//                if (!TextUtils.isEmpty(questionnaireJSON)) {
//                    intent.putExtra("questionnaire_JSON", questionnaireJSON);
//                } else {
//                    Toast.makeText(LogInActivity.this, "Load questionnaires failed. Please try " +
//                            "again", Toast.LENGTH_LONG).show();
//                }
                intent.putExtra("proband", proband);

                // 5. TODO: use this intent to start AnswerQuestionActivity
                startActivity(intent);




            }
        });

        // ==============================================================================

    }

}
