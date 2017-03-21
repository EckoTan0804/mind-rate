package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

/**
 * This is the activity for proband's log in.
 *
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.activity</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/13:22:11</br>
 * </p>
 */
public class LogInActivity extends BaseActivity {

    private static final String TAG = "LogInActivity";


//    public static final String SERVER = "http://ws16-pse-esm1.teco.edu";

    public static final String SERVER = "http://46.101.211.35";
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
    private ProgressBar progressBar;

    // ======================================================

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

    /**
     * Initialize view of the activity
     */
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
        
        // ==================== progress bar ===========================
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
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
//                        ll_secondShowUp.setVisibility(View.VISIBLE);
                        btn_newPage.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    HttpUtil.sendRequestWithOkHttp(SERVER + "/proband_info/1/", new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(LogInActivity.this, R.string.check_network, Toast.LENGTH_LONG).show();

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
                                        progressBar.setVisibility(View.GONE);
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
                                        ll_secondShowUp.setVisibility(View.VISIBLE);
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
                    HttpUtil.post(SERVER + "/receive_answer/", probandJSON, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(LogInActivity.this, R.string.upload_fail, Toast.LENGTH_LONG)
                                    .show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseMsg = response.body().string();
                            Log.w(TAG, "onResponse: "+ responseMsg);
                        }
                    });

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
                            intent.putExtra("proband", proband);

                            // set isLogIn of MainActivity = true
                            MainActivity.setIsLogIn(true);
                            PreferenceUtil.commitBoolean("isLogIn", true);

                            // use this intent to start AnswerQuestionActivity
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        // ==============================================================================
    }

    /**
     * Check whether the input in <code>editText</code> is empty
     * @param editText
     * @return
     */
    private boolean isEditTextEmpty(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            handleEditTextEmpty(editText);
            return true;
        }
        return false;
    }

    /**
     * Handle the situation that the input of <code>editText</code> is empty
     * @param editText
     */
    private void handleEditTextEmpty(EditText editText) {
        editText.setError(getString(R.string.input_empty_error));
    }

    // ====================== setters and getters =======================================


    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getProbandID() {
        return probandID;
    }

    public void setProbandID(String probandID) {
        this.probandID = probandID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public static String getSERVER() {
        return SERVER;
    }

    public EditText getEdtTxt_studyID() {
        return edtTxt_studyID;
    }

    public void setEdtTxt_studyID(EditText edtTxt_studyID) {
        this.edtTxt_studyID = edtTxt_studyID;
    }

    public EditText getEdtTxt_probandID() {
        return edtTxt_probandID;
    }

    public void setEdtTxt_probandID(EditText edtTxt_probandID) {
        this.edtTxt_probandID = edtTxt_probandID;
    }

    public DatePicker getDtPk_birthday() {
        return dtPk_birthday;
    }

    public void setDtPk_birthday(DatePicker dtPk_birthday) {
        this.dtPk_birthday = dtPk_birthday;
    }

    public RadioGroup getRdog_chooseGender() {
        return rdog_chooseGender;
    }

    public void setRdog_chooseGender(RadioGroup rdog_chooseGender) {
        this.rdog_chooseGender = rdog_chooseGender;
    }

    public EditText getEdtTxt_occuptaion() {
        return edtTxt_occuptaion;
    }

    public void setEdtTxt_occuptaion(EditText edtTxt_occuptaion) {
        this.edtTxt_occuptaion = edtTxt_occuptaion;
    }

    public Button getBtn_probandLogIn() {
        return btn_probandLogIn;
    }

    public void setBtn_probandLogIn(Button btn_probandLogIn) {
        this.btn_probandLogIn = btn_probandLogIn;
    }

    public boolean isNeedBirthday() {
        return needBirthday;
    }

    public void setNeedBirthday(boolean needBirthday) {
        this.needBirthday = needBirthday;
    }

    public boolean isNeedGender() {
        return needGender;
    }

    public void setNeedGender(boolean needGender) {
        this.needGender = needGender;
    }

    public boolean isNeedOccupation() {
        return needOccupation;
    }

    public void setNeedOccupation(boolean needOccupation) {
        this.needOccupation = needOccupation;
    }
}
