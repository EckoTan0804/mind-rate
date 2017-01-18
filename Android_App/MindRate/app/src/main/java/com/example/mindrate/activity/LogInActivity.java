package com.example.mindrate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mindrate.R;

import java.util.Calendar;
import java.util.TimeZone;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{


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
    private RadioButton rdoBtn_gender_male;
    private RadioButton rdoBtn_gender_famale;
    private EditText edtTxt_occuptaion;
    private Button btn_probandLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initView();

    }

    private void  initView() {
        edtTxt_studyID = (EditText) findViewById(R.id.study_id);
        edtTxt_probandID = (EditText) findViewById(R.id.proband_id);
        dtPk_birthday = (DatePicker) findViewById(R.id.choose_birthday);
        rdoBtn_gender_male = (RadioButton) findViewById(R.id.male);
        rdoBtn_gender_famale = (RadioButton) findViewById(R.id.female);
        edtTxt_occuptaion = (EditText) findViewById(R.id.occupation);
        btn_probandLogIn = (Button) findViewById(R.id.proband_log_in);

        // ================= DatePicker ============================
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

        rdoBtn_gender_male.setOnCheckedChangeListener(this);
        rdoBtn_gender_famale.setOnCheckedChangeListener(this);
        btn_probandLogIn.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch(compoundButton.getId()){
            case R.id.male:
                if (isChecked) {
                    gender = "male";
//                    Toast.makeText(LogInActivity.this, this.gender, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.female:
                if (isChecked) {
                    gender = "female";
//                    Toast.makeText(LogInActivity.this, this.gender, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                
                break;
        }
    }
}
