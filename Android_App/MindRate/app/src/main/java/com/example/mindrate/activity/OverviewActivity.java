package com.example.mindrate.activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.fragment.AboutUsFragment;
import com.example.mindrate.fragment.ChooseQuestionnaireFragment;
import com.example.mindrate.fragment.ProbandProfileFragment;
import com.example.mindrate.fragment.SettingFragment;
import com.example.mindrate.fragment.WelcomeFragment;
import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;
import com.example.mindrate.gson.TriggerEvent;
import com.example.mindrate.gson.TriggerEventManager;
import com.example.mindrate.util.JsonUtil;
import com.example.mindrate.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_ORIENTATION;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_PROXIMITY;
import static android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY;
import static android.hardware.Sensor.TYPE_ROTATION_VECTOR;

public class OverviewActivity extends BaseActivity {
    private static final String TAG = "OverviewActivity";

    private Proband proband;
    private List<Questionnaire> questionnaireList;

    // ==================== View components ==================================
    private DrawerLayout mDrawerLayout;
    private Button btn_nav;
    private NavigationView navView;
    private TextView tv_title;
    //private SensorManager sensorManager;
    //private List<Sensor> allSensors;
    private TriggerEventManager triggerEventManager;
    private RelativeLayout title;

    // =======================================================================
    WelcomeFragment welcomeFragment = new WelcomeFragment();
    ProbandProfileFragment probandProfileFragment = new ProbandProfileFragment();
    ChooseQuestionnaireFragment chooseQuestionnaireFragment = new ChooseQuestionnaireFragment();
    AboutUsFragment aboutUsFragment = new AboutUsFragment();
    SettingFragment settingFragment = new SettingFragment();

    // =======================================================================

   /* private DeviceSensorService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection(){

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
        @Override
        public void onServiceConnected(ComponentName name,  IBinder service) {
            Log.d(TAG,"bindStart");
            myBinder = (DeviceSensorService.MyBinder)service;
            //Log.d(TAG,"setTEM");
            myBinder.setTriggerEventManager(triggerEventManager);
            //Log.d(TAG,"tranTEM");
            myBinder.transferTriggerEventManager();
        }
    };*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        initFromIntent();
        initView();

        //        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //        allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //        tEM =  new TriggerEventManager();
        //sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //        tEM =  new TriggerEventManager();

        initTestData();
        //triggerEventManager = new TriggerEventManager(this.questionnaireList);
        Log.i(TAG, "TEM created in Activity");
        //sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //        tEM =  new TriggerEventManager();
        //Intent intent = new Intent(OverviewActivity.this, DeviceSensorService.class);

        //Log.i(TAG,"Service onStart_____");
        //Intent bindServiceIntent = new Intent(OverviewActivity.this,DeviceSensorService.class);
        //bindService(bindServiceIntent,connection,BIND_AUTO_CREATE);
        Log.i(TAG, "Service onBind_____");


        //        tv_questionText.setText(Utility.createJSON(this.proband));

    }

    @Override
    protected void onResume() {
        super.onResume();
        //        for (Sensor sensor : allSensors) {
        //            // sensorManager.registerListener(listener, sensor, SENSOR_DELAY_GAME);
        //            Intent startServiceIntent = new Intent(OverviewActivity.this,
        // DeviceSensorService
        //                    .class);
        //            startService(startServiceIntent);
        /*for(Sensor sensor :allSensors ) {
           // sensorManager.registerListener(listener, sensor, SENSOR_DELAY_GAME);
>>>>>>> 1d08a2433890d356671ee32d525bccad62e01e91
            //(listener, sensor,SensorManager.SENSOR_DELAY_GAME);  
            //this.addSensorListener(sensor);
        }*/


        //        }
    }

    private void initFromIntent() {
        Intent intent = getIntent();

        // questionnaires

        String questionnaireJSON = intent.getStringExtra("questionnaire_JSON");
        if (TextUtils.isEmpty(questionnaireJSON)) {
            questionnaireJSON = PreferenceUtil.getString("questionnaireJSON", "");
            //            SharedPreferences pref = getSharedPreferences("questionnaire",
            // MODE_PRIVATE);
            //            questionnaireJSON = pref.getString("questionnaireJSON", "");
        }
        this.questionnaireList = JsonUtil.fromJsonToQuestionnaireList(questionnaireJSON);

        // proband
        Proband probandFromLogIn = intent.getParcelableExtra("proband");
        if (probandFromLogIn != null) {
            this.proband = probandFromLogIn;
        } else {
            String probandJSON = PreferenceUtil.getString("probandJSON", "");
            this.proband = JsonUtil.fromJsonToProband(probandJSON);
        }

    }

    private void initView() {

        title = (RelativeLayout) findViewById(R.id.title_overview);

        // =================== TextView title_overview ===============================
        tv_title = (TextView) findViewById(R.id.title_title);
        // ==================================================================

        tv_title.setText("Mind Rate");
        replaceFragment(welcomeFragment);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // =================== Button nav ===================================
        btn_nav = (Button) findViewById(R.id.nav);
        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // ==================================================================

        // =================== Navigation Menu ==============================
        navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_profile);
        navView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        tv_title.setText("Profile");
                        replaceFragment(probandProfileFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_questionnaire_list:
                        tv_title.setText("Questionnaires");
                        replaceFragment(chooseQuestionnaireFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        tv_title.setText("About Us");
                        replaceFragment(aboutUsFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        tv_title.setText("Settings");
                        replaceFragment(settingFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    default:

                        break;
                }
                return true;
            }
        });

        // ==================================================================

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.switch_fragment, fragment);
        transition.addToBackStack(null);
        transition.commit();
    }


    private Questionnaire removeQuestionnaire(String questionnaireID) {
        Questionnaire removeQuestionnaire = null;
        Iterator<Questionnaire> iterator = this.questionnaireList.iterator();
        while (iterator.hasNext()) {
            Questionnaire q = iterator.next();
            if (q.getQuestionnaireID().equals(questionnaireID)) {
                removeQuestionnaire = q;
                if (q.isAnswered()) {
                    iterator.remove();
                }
                break;
            }
        }
        return removeQuestionnaire;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final String answeredQuestionnaireID = data.getStringExtra("answered " +
                                                                                       "questionnaire " +
                                                                                       "ID");
                    // remove the answered questionnaire from questionnaireList
                    removeQuestionnaire(answeredQuestionnaireID);
                    // save the new questionnaireList locally
                    PreferenceUtil.commitString("questionnaireJSON", JsonUtil.createJSON(
                            this.questionnaireList));
                    // notify questionnaireList's adapter that the list has been changed
                    chooseQuestionnaireFragment.getAdapter().notifyDataSetChanged();
                }
                break;
            default:

                break;
        }
    }

    public void switchLanguageImmediately(String language) {
        super.switchLanguage(language);
        finish();
        Intent intent = new Intent(OverviewActivity.this, OverviewActivity.class);
        startActivity(intent);

    }

    public View ActionBar() {
        return this.title;
    }

    // ======================= setters and getters =============================

    public Proband getProband() {
        return proband;
    }

    public void setProband(Proband proband) {
        this.proband = proband;
    }

    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    // test data
    private void initTestData() {

        //        String list = PreferenceUtil.getString("questionnaireJSON", "");
        //        if (!TextUtils.isEmpty(list)) {
        //            questionnaireList = JsonUtil.fromJsonToQuestionnaireList(list);
        //        } else {
        //
        //            questionnaireList = new ArrayList<>();
        //
        //            Questionnaire questionnaire = new Questionnaire("A", "2017.1.2 14:00",
        // "2017.2.2 " +
        //                    "14:00");
        //
        //            // q1
        //            ArrayList<Option> optionList = new ArrayList<>();
        //            optionList.add(new Option("At home", "Q3"));
        //            optionList.add(new Option("At work", "Q3"));
        //            optionList.add(new Option("on the way", "Q2"));
        //            Question q1 = new Question("Where are you?", new SingleChoice(optionList),
        // "Q1");
        //            questionnaire.addQuestion(q1);
        //
        //            // q2
        //            Question q2 = new Question("Where are you heading to?", new TextAnswer(),
        // "Q2");
        //            questionnaire.addQuestion(q2);
        //
        //            // q3
        //            Question q3 = new Question("How are you feeling?", new DragScale(10), "Q3");
        //            questionnaire.addQuestion(q3);
        //
        //            // q4
        //            ArrayList<Option> optionArrayList = new ArrayList<>();
        //            optionArrayList.add(new Option("Swimming", null));
        //            optionArrayList.add(new Option("Reading", null));
        //            optionArrayList.add(new Option("Coding", null));
        //            optionArrayList.add(new Option("Studying", null));
        //            Question q4 = new Question("What's ur hobby?", new MultipleChoice
        // (optionArrayList),
        //                                       "Q4");
        //            questionnaire.addQuestion(q4);
        //
        //            // q5
        //            ArrayList<Option> options = new ArrayList<>();
        //            options.add(new Option("very bad", null));
        //            options.add(new Option("bad", null));
        //            options.add(new Option("so so", null));
        //            options.add(new Option("good", null));
        //            options.add(new Option("very good!", null));
        //            Question q5 = new Question("Do you like this app?", new StepScale(options),
        // "Q5");
        //            questionnaire.addQuestion(q5);
        //
        //
        //            questionnaireList.add(questionnaire);
        //            questionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        //            questionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));
        //        }

        questionnaireList = new ArrayList<>();

        Questionnaire questionnaire = new Questionnaire("A", "2017.1.2 14:00", "2017.2.2 14:00");
        // q1
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("At home", "Q3"));
        optionList.add(new Option("At work", "Q3"));
        optionList.add(new Option("on the way", "Q2"));
        Question q1 = new Question("Where are you?", new SingleChoice(optionList), "Q1");
        questionnaire.addQuestion(q1);

        // q2
        Question q2 = new Question("Where are you heading to?", new TextAnswer(), "Q2");
        questionnaire.addQuestion(q2);

        // q3
        Question q3 = new Question("How are you feeling?", new DragScale(10), "Q3");
        questionnaire.addQuestion(q3);

        // q4
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(new Option("Swimming", null));
        optionArrayList.add(new Option("Reading", null));
        optionArrayList.add(new Option("Coding", null));
        optionArrayList.add(new Option("Studying", null));
        Question q4 = new Question("What's ur hobby?", new MultipleChoice(optionArrayList), "Q4");
        questionnaire.addQuestion(q4);

        // q5
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("very bad", null));
        options.add(new Option("bad", null));
        options.add(new Option("so so", null));
        options.add(new Option("good", null));
        options.add(new Option("very good!", null));
        Question q5 = new Question("Do you like this app?", new StepScale(options), "Q5");
        questionnaire.addQuestion(q5);
        TriggerEvent triggerEvent1 = new TriggerEvent(questionnaire.getQuestionnaireID());
        triggerEvent1.setLight(true);
        triggerEvent1.setAirTemperature(true);
        questionnaire.setTriggerEvent(triggerEvent1);


        questionnaireList.add(questionnaire);
        //        questionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        //        questionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));
        //        TriggerEventManager triggerEventManager = TriggerEventManager
        // .getTriggerEventManager();
        //        triggerEventManager.setQuestionnaireList(questionnaireList);
    }

    public void addSensorListener(Sensor sensor) {
        switch (sensor.getType()) {
            case TYPE_ACCELEROMETER:
                //

                break;
            case TYPE_AMBIENT_TEMPERATURE:
                //
                break;
            case TYPE_GRAVITY:
                //
                break;
            case TYPE_GYROSCOPE:
                //
                break;
            case TYPE_LIGHT:
                //
                break;
            case TYPE_LINEAR_ACCELERATION:
                //
                break;
            case TYPE_MAGNETIC_FIELD:
                //
                break;
            case TYPE_ORIENTATION:
                //
                break;
            case TYPE_PRESSURE:
                //
                break;
            case TYPE_PROXIMITY:
                //
                break;
            case TYPE_RELATIVE_HUMIDITY:
                //
                break;
            case TYPE_ROTATION_VECTOR:
                //
                break;
            default:
                //TYPE_TEMPERATURE
                break;


        }


    }

}
