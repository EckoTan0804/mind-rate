package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindrate.R;
import com.example.mindrate.gson.Birthday;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.util.HttpUtil;
import com.example.mindrate.util.JsonUtil;
import com.example.mindrate.util.PreferenceUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LogInActivity extends BaseActivity {


    public static final String SERVER = "http://ws16-pse-esm1.teco.edu";
    private final String MALE = "male";
    private final String FEMALE = "female";

    private static int logInPage = 1;
    private static int LOG_IN_LAST_PAGE = 2;

    private String questionnaireJSON;
    private Proband proband;

    // =================== proband ==========================
    private String studyID;
    private String probandID;
    private int year;
    private int month;
    private int day;
    private String gender = MALE;
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

    private boolean needBirthday;
    private boolean needGender;
    private boolean needOccupation;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bundle data = msg.getData();
//            needBirthday = data.getBoolean("birthday");
//            needGender = data.getBoolean("gender");
//            needOccupation = data.getBoolean("occupation");
//            if (!needBirthday) {
//                TextView birthday = (TextView) findViewById(R.id.log_in_birthday);
//                birthday.setVisibility(View.GONE);
//                dtPk_birthday.setVisibility(View.GONE);
//            }
//            if (!needGender) {
//                TextView gender = (TextView) findViewById(R.id.log_in_gender);
//                gender.setVisibility(View.GONE);
//                rdog_chooseGender.setVisibility(View.GONE);
//            }
//            if (!needOccupation) {
//                TextView occupation = (TextView) findViewById(R.id.log_in_occupation);
//                occupation.setVisibility(View.GONE);
//                edtTxt_occuptaion.setVisibility(View.GONE);
//            }
//
//
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //        ActivityManager.addActivity(this);
        initView();

    }

    private void initView() {

        final LinearLayout ll_firstShowUp = (LinearLayout) findViewById(R.id.log_in_first_show_up);
        final LinearLayout ll_secondShowUp = (LinearLayout) findViewById(
                R.id.log_in_second_show_up);


        // ==================== study ID ===============================
        edtTxt_studyID = (EditText) findViewById(R.id.study_id);
        // =============================================================

        // ==================== proband ID =============================
        edtTxt_probandID = (EditText) findViewById(R.id.proband_id);
        // =============================================================

        // ==================== nextPage_btn ===========================
        final Button btn_newPage = (Button) findViewById(R.id.log_in_next_page);
        btn_newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                if (isEditTextEmpty(edtTxt_studyID) || isEditTextEmpty
                // (edtTxt_probandID)) {
                //                    isEditTextEmpty(edtTxt_studyID);
                //                    isEditTextEmpty(edtTxt_probandID);
                boolean studyIDEmpty = isEditTextEmpty(edtTxt_studyID);
                boolean probandIDEmpty = isEditTextEmpty(edtTxt_probandID);
                if (!studyIDEmpty && !probandIDEmpty) {
                    logInPage++;
                    if (logInPage == LOG_IN_LAST_PAGE) {
                        ll_firstShowUp.setVisibility(View.GONE);
                        ll_secondShowUp.setVisibility(View.VISIBLE);
                        btn_newPage.setVisibility(View.GONE);
                    }

                    HttpUtil.sendRequestWithOkHttp(SERVER + "/proband_info/1/", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                needBirthday = obj.getBoolean("birthday");
                                needGender = obj.getBoolean("gender");
                                needOccupation = obj.getBoolean("occupation");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (needBirthday) {
                                            TextView birthday = (TextView) findViewById(R.id.log_in_birthday);
                                            birthday.setVisibility(View.VISIBLE);
                                            dtPk_birthday.setVisibility(View.VISIBLE);
                                        }
                                        if (needGender) {
                                            TextView gender = (TextView) findViewById(R.id.log_in_gender);
                                            gender.setVisibility(View.VISIBLE);
                                            rdog_chooseGender.setVisibility(View.VISIBLE);
                                        }
                                        if (needOccupation) {
                                            TextView occupation = (TextView) findViewById(R.id.log_in_occupation);
                                            occupation.setVisibility(View.VISIBLE);
                                            edtTxt_occuptaion.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
//                                Message msg = new Message();
//                                Bundle data = new Bundle();
//                                data.putBoolean("birthday", obj.getBoolean("birthday"));
//                                data.putBoolean("gender", obj.getBoolean("gender"));
//                                data.putBoolean("occupation", obj.getBoolean("occupation"));
//                                msg.setData(data);
//                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {

                            }

                        }
                    });


                }
            }

        });

        // ================= DatePicker ================================

        dtPk_birthday = (DatePicker) findViewById(R.id.choose_birthday);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        dtPk_birthday.init(year, month++, day, new DatePicker.OnDateChangedListener() {
            /*
             * Explanation for month++ : calendar.get(Calendar.MONTH) = actual month - 1
             * e.g: now is January, but calendar.get(Calendar.MONTH) = 0
             * In order to maintain the consistence, month have to plus 1 after initializing the
             * datePicker
             */
            @Override
            public void onDateChanged(DatePicker datePicker, int changedYear, int monthOfYear, int
                    dayOfMonth) {
                year = changedYear;
                month = monthOfYear + 1;
                day = dayOfMonth;
            }
        });
//        if (!needBirthday) {
//            TextView birthday = (TextView) findViewById(R.id.log_in_birthday);
//            birthday.setVisibility(View.GONE);
//            dtPk_birthday.setVisibility(View.GONE);
//        }

        // ============================================================

        // =============== gender =====================================
        rdog_chooseGender = (RadioGroup) findViewById(R.id.choose_gender);

        rdog_chooseGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = MALE;
                        //                        Toast.makeText(LogInActivity.this, gender,
                        // Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.female:
                        gender = FEMALE;
                        //                        Toast.makeText(LogInActivity.this, gender,
                        // Toast.LENGTH_SHORT).show();
                        break;
                    default:

                        break;
                }
            }
        });
//        if (!needGender) {
//            TextView gender = (TextView) findViewById(R.id.log_in_gender);
//            gender.setVisibility(View.GONE);
//            rdog_chooseGender.setVisibility(View.GONE);
//        }

        // ==============================================================================

        // ========================= occupation =========================================
        edtTxt_occuptaion = (EditText) findViewById(R.id.occupation);
//        if (!needOccupation) {
//            TextView occupation = (TextView) findViewById(R.id.log_in_occupation);
//            occupation.setVisibility(View.GONE);
//            edtTxt_occuptaion.setVisibility(View.GONE);
//        }
        // ==============================================================================

        // ========================= log in =============================================

        btn_probandLogIn = (Button) findViewById(R.id.proband_log_in);

        btn_probandLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check whether the input for occupation is empty

                boolean occupationEmpty = isEditTextEmpty(edtTxt_occuptaion);

                if (!(occupationEmpty && edtTxt_occuptaion.getVisibility() == View.VISIBLE)) {

                    // create Proband instance
                    studyID = edtTxt_studyID.getText().toString();
                    probandID = edtTxt_probandID.getText().toString();
                    occupation = edtTxt_occuptaion.getText().toString();
                    proband = new Proband(studyID, probandID,
                                                  new Birthday(year, month, day),
                                                  gender, occupation);

                    // create probandJSON and save it locally
                    String probandJSON = JsonUtil.createJSON(proband);
                    PreferenceUtil.commitString("probandJSON", probandJSON);

                    // upload probandJSON to server
//                    try {
//                        questionnaireJSON = HttpUtil.post(SERVER, probandJSON);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//
//                    }

                    // download Questionnaires and save it
                    String address = SERVER + "/download/" + studyID + "/";
                    HttpUtil.sendRequestWithOkHttp(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(LogInActivity.this, R.string.load_fail, Toast.LENGTH_SHORT)
                                    .show();
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            questionnaireJSON = response.body().string();
                            PreferenceUtil.commitString("questionnaireJSON", questionnaireJSON);

                            // put proband & questionnaireJSON into intent
                            Intent intent = new Intent(LogInActivity.this, OverviewActivity.class);
                            if (!TextUtils.isEmpty(questionnaireJSON)) {
                                intent.putExtra("questionnaire_JSON", questionnaireJSON);
                            }
                            //                    } else {
                            //                        Toast.makeText(LogInActivity.this, "Load questionnaires failed.Please try " +
                            //                        "again", Toast.LENGTH_LONG).show();
                            //                    }
                            intent.putExtra("proband", proband);

                            // set isLogIn of MainActivity = true
                            MainActivity.setIsLogIn(true);
                            PreferenceUtil.commitBoolean("isLogIn", true);

                            // use this intent to start AnswerQuestionActivity
                            startActivity(intent);
                        }
                    });

//                    PreferenceUtil.commitString("questionnaireJSON", questionnaireJSON);
//
//                    // put proband & questionnaireJSON into intent
//                    Intent intent = new Intent(LogInActivity.this, OverviewActivity.class);
//                    if (!TextUtils.isEmpty(questionnaireJSON)) {
//                        intent.putExtra("questionnaire_JSON", questionnaireJSON);
//                    }
////                    } else {
////                        Toast.makeText(LogInActivity.this, "Load questionnaires failed.Please try " +
////                        "again", Toast.LENGTH_LONG).show();
////                    }
//                    intent.putExtra("proband", proband);
//
//                    // set isLogIn of MainActivity = true
//                    MainActivity.setIsLogIn(true);
//                    PreferenceUtil.commitBoolean("isLogIn", true);
//
//                    // use this intent to start AnswerQuestionActivity
//                    startActivity(intent);
                }
            }
        });

        // ==============================================================================
    }

    private boolean isEditTextEmpty(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            handleEditTextEmpty(editText);
            return true;
        }
        return false;
    }

    private void handleEditTextEmpty(EditText editText) {
        editText.setError(getString(R.string.input_empty_error));
    }



}
