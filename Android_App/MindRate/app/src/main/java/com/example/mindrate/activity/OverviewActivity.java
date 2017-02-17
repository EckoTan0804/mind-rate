package com.example.mindrate.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.example.mindrate.service.DeviceSensorService;
import com.example.mindrate.util.JsonUtil;
import com.example.mindrate.util.PreferenceUtil;
import com.example.mindrate.util.TimeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class OverviewActivity extends BaseActivity {
    private static final String TAG = "OverviewActivity";
    private static OverviewActivity instance = null;
    //myBroadcastReceiver receiver = new  myBroadcastReceiver();

    private Proband proband;

    private List<Questionnaire> allQuestionnaireList; // all questionnaires
    private List<Questionnaire> triggeredQuestionnaireList;

    private Questionnaire selectedQuestionnaire;
    private int selectedQuestionnaireIndex;
    private boolean isFirstLoad = true;
    private List<Questionnaire> triggeredByTimeQuestionnaireList = new ArrayList<>();
    private List<Questionnaire> triggeredByDateQuestionnaireList = new ArrayList<>();


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
    //    WelcomeFragment welcomeFragment = new WelcomeFragment();
    //    ProbandProfileFragment probandProfileFragment = new ProbandProfileFragment();
    //    ChooseQuestionnaireFragment chooseQuestionnaireFragment = new
    // ChooseQuestionnaireFragment();
    //    AboutUsFragment aboutUsFragment = new AboutUsFragment();
    //    SettingFragment settingFragment = new SettingFragment();

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

        if (this.allQuestionnaireList == null) {
            this.allQuestionnaireList = new ArrayList<>();
        }
        if (this.triggeredQuestionnaireList == null) {
            this.triggeredQuestionnaireList = new ArrayList<>();
        }
        initTestData();
        addTriggeredByTimeQuestionnaire();
        addTriggeredByDatetimeQuestionnaire();

        Log.i(TAG, "TEM created in Activity");
        instance = this;


    }

    @Override
    protected void onResume() {
        super.onResume();
        triggerEventManager = TriggerEventManager.getTriggerEventManager();
        triggerEventManager.setOverviewActivity(instance);
        Intent startServiceIntent = new Intent(OverviewActivity.this, DeviceSensorService.class);
        //===========stop service===============

        startService(startServiceIntent);
        //=============================
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setEveryTriggeredDateQuestionnaireAlarm(alarmManager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
        Log.i(TAG, "has unregist....");

    }

    private void initFromIntent() {

        Intent intent = getIntent();

        // questionnaires

        String questionnaireJSON = intent.getStringExtra("questionnaire_JSON");
        if (TextUtils.isEmpty(questionnaireJSON)) {
            questionnaireJSON = PreferenceUtil.getString("questionnaireJSON", "");
        }
        this.allQuestionnaireList = JsonUtil.fromJsonToQuestionnaireList(questionnaireJSON);

        // proband
        Proband probandFromLogIn = intent.getParcelableExtra("proband");
        if (probandFromLogIn != null) {
            this.proband = probandFromLogIn;
        } else {
            String probandJSON = PreferenceUtil.getString("probandJSON",
                                                          "");
            this.proband = JsonUtil.fromJsonToProband(probandJSON);
        }

        // pendingIntent from Notification
        String fromIntent = intent.getStringExtra("notityToAnswer");
        if (!TextUtils.isEmpty(fromIntent)) {
            if (fromIntent.equals("chooseQuestionnaireFragment")) {
                isFirstLoad = false;
                replaceFragment(chooseQuestionnaireFragment);
            }

        }

    }

    private void initView() {

        title = (RelativeLayout) findViewById(R.id.title_overview);

        // =================== TextView title_overview ===============================
        tv_title = (TextView) findViewById(R.id.title_title);
        // ==================================================================

        if (isFirstLoad) {
            replaceFragment(welcomeFragment);
        }

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
        navView.setItemTextAppearance(R.style.TextAppearance_FontPath);
        navView.setCheckedItem(R.id.nav_profile);
        navView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        tv_title.setText(R.string.nav_profile);
                        replaceFragment(probandProfileFragment);
                        //                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_questionnaire_list:
                        tv_title.setText(R.string.nav_questionnaire);
                        replaceFragment(chooseQuestionnaireFragment);
                        //                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about_us:
                        tv_title.setText(R.string.nav_about_us);
                        replaceFragment(aboutUsFragment);
                        //                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        tv_title.setText(R.string.nav_settings);
                        replaceFragment(settingFragment);
                        //                        mDrawerLayout.closeDrawers();
                        break;
                    default:

                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        // ==================================================================

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.switch_fragment,
                           fragment);
        transition.addToBackStack(null);
        transition.commit();
    }


    //    private Questionnaire removeQuestionnaireFromTriggeredQuestionnaireList(String
    // questionnaireID) {
    //        Questionnaire removeQuestionnaire = null;
    //        Iterator<Questionnaire> iterator = this.allQuestionnaireList.iterator();
    //        while (iterator.hasNext()) {
    //            Questionnaire q = iterator.next();
    //            if (q.getQuestionnaireID().equals(questionnaireID)) {
    //                removeQuestionnaire = q;
    //                if (q.isAnswered()) {
    //                    iterator.remove();
    //                }
    //                break;
    //            }
    //        }
    //        return removeQuestionnaire;
    //    }
    //
    //    private void removeQuestionnaireFromTriggeredQuestionnaireList(Questionnaire
    // selectedQuestionnaire) {
    //        int removeIndex = this.allQuestionnaireList.indexOf(selectedQuestionnaire);
    //        if (selectedQuestionnaire.isAnswered()) {
    //            this.allQuestionnaireList.remove(removeIndex);
    //        }
    //    }

    private void removeSelectedQuestionByIndex() {
        this.triggeredQuestionnaireList.remove(this.selectedQuestionnaireIndex);
    }

    private Questionnaire getQuestionnaire(String questionnaireID) {
        Questionnaire questionnaire = null;
        for (Questionnaire tempQuestionnaire : this.allQuestionnaireList) {
            if (tempQuestionnaire.getQuestionnaireID()
                    .equals(questionnaireID)) {
                questionnaire = tempQuestionnaire;
            }
        }
        return questionnaire;
    }


    public void addQuestionnaireToTriggeredQuestionnaireList(String questionnaireID) {
        Questionnaire questionnaire = getQuestionnaire(questionnaireID);
        this.addQuestionnaireToTriggeredQuestionnaireList(questionnaire);
    }


    private void addQuestionnaireToTriggeredQuestionnaireList(Questionnaire questionnaire) {
        questionnaire.trigger(OverviewActivity.this);
        if (!this.triggeredQuestionnaireList.isEmpty()) {
            this.triggeredQuestionnaireList.add(questionnaire);
            chooseQuestionnaireFragment.getAdapter()
                    .notifyDataSetChanged();
        } else {
            this.triggeredQuestionnaireList.add(questionnaire);
        }
    }

    public void addQuestionnaireToTriggeredQuestionnaireList(List<String> questionnaireIdList) {

    }

    @Override
    protected void onActivityResult(int requestCode,
            int resultCode,
            Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //                    final String answeredQuestionnaireID = data
                    // .getStringExtra("answered " +
                    //
                    //            "questionnaire " +
                    //
                    //            "ID");
                    // remove the answered questionnaire from allQuestionnaireList
                    //                    removeQuestionnaireFromTriggeredQuestionnaireList
                    // (answeredQuestionnaireID);
                    //                    this.selectedQuestionnaire.setAnswered(true);
                    removeSelectedQuestionByIndex();
                    // save the new triggeredQuestionnaireList locally
                    PreferenceUtil.commitString("questionnaireJSON",
                                                JsonUtil.createJSON(
                                                        this.triggeredQuestionnaireList));
                    // notify allQuestionnaireList's adapter that the list has been changed
                    chooseQuestionnaireFragment.getAdapter()
                            .notifyDataSetChanged();
                }
                break;
            default:

                break;
        }
    }

    public void switchLanguageImmediately(String language) {
        super.switchLanguage(language);
        finish();
        Intent intent = new Intent(OverviewActivity.this,
                                   OverviewActivity.class);
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

    public List<Questionnaire> getAllQuestionnaireList() {
        return allQuestionnaireList;
    }

    public void setAllQuestionnaireList(List<Questionnaire> allQuestionnaireList) {
        this.allQuestionnaireList = allQuestionnaireList;
    }

    public Questionnaire getSelectedQuestionnaire() {
        return selectedQuestionnaire;
    }

    public void setSelectedQuestionnaire(Questionnaire selectedQuestionnaire) {
        this.selectedQuestionnaire = selectedQuestionnaire;
    }

    public List<Questionnaire> getTriggeredQuestionnaireList() {
        return triggeredQuestionnaireList;
    }

    public void setTriggeredQuestionnaireList(List<Questionnaire> triggeredQuestionnaireList) {
        this.triggeredQuestionnaireList = triggeredQuestionnaireList;
    }

    public int getSelectedQuestionnaireIndex() {
        return selectedQuestionnaireIndex;
    }

    public void setSelectedQuestionnaireIndex(int selectedQuestionnaireIndex) {
        this.selectedQuestionnaireIndex = selectedQuestionnaireIndex;
    }

    // =========================================================================

    // test data
    private void initTestData() {

        //        String list = PreferenceUtil.getString("questionnaireJSON", "");
        //        if (!TextUtils.isEmpty(list)) {
        //            allQuestionnaireList = JsonUtil.fromJsonToQuestionnaireList(list);
        //        } else {
        //
        //            allQuestionnaireList = new ArrayList<>();
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
        //            allQuestionnaireList.add(questionnaire);
        //            allQuestionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        //            allQuestionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));
        //        }


        // allQuestionnaireList = new ArrayList<>();

        Questionnaire questionnaireA = new Questionnaire("A",
                                                         2);
        // q1
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("At home",
                                  "Q3"));
        optionList.add(new Option("At work",
                                  "Q3"));
        optionList.add(new Option("on the way",
                                  "Q2"));
        Question q1 = new Question("Where are you?",
                                   new SingleChoice(optionList),
                                   "Q1");
        questionnaireA.addQuestion(q1);

        // q2
        Question q2 = new Question("Where are you heading to?",
                                   new TextAnswer(),
                                   "Q2");
        questionnaireA.addQuestion(q2);

        // q3
        Question q3 = new Question("How are you feeling?",
                                   new DragScale(10),
                                   "Q3");
        questionnaireA.addQuestion(q3);

        // q4
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(new Option("Swimming",
                                       null));
        optionArrayList.add(new Option("Reading",
                                       null));
        optionArrayList.add(new Option("Coding",
                                       null));
        optionArrayList.add(new Option("Studying",
                                       null));
        Question q4 = new Question("What's ur hobby?",
                                   new MultipleChoice(optionArrayList),
                                   "Q4");
        questionnaireA.addQuestion(q4);

        // q5
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("very bad",
                               null));
        options.add(new Option("bad",
                               null));
        options.add(new Option("so so",
                               null));
        options.add(new Option("good",
                               null));
        options.add(new Option("very good!",
                               null));
        Question q5 = new Question("Do you like this app?",
                                   new StepScale(options),
                                   "Q5");
        questionnaireA.addQuestion(q5);
        TriggerEvent triggerEvent1 = new TriggerEvent(questionnaireA.getQuestionnaireID());
        //triggerEvent1.setLight(true);
        //triggerEvent1.setAirTemperature(true);
        //triggerEvent1.setTime("10-55-10");
        Date date = TimeUtil.getCurrentTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(TimeUtil.getCurrentTime());
        calendar.add(Calendar.MINUTE, 1);
        triggerEvent1.setDateTime(calendar.getTime());
        //        questionnaireA.setTriggerEvent(triggerEvent1);
        triggerEvent1.setAmbientTemperatureMaxValue(20);
        triggerEvent1.setAmbientTemperatureMinValue(19);
        questionnaireA.setTriggerEvent(triggerEvent1);
        questionnaireA.setValid(true);

        allQuestionnaireList.add(questionnaireA);
        TriggerEventManager triggerEventManager = TriggerEventManager.getTriggerEventManager();
        triggerEventManager.setQuestionnaireList(allQuestionnaireList);
        for (Questionnaire questionnaire1 : allQuestionnaireList) {
            triggerEventManager.addObserver(questionnaire1);
        }



        //        List<Questionnaire> testquestionnaireList = new ArrayList<>();
        //        testquestionnaireList.add(questionnaireA);
        //        questionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        //        questionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));

    }

    public static OverviewActivity getInstance() {
        return instance;

    }


    //想法是 create的时候先把有date的触发条件的问卷加进来。然后用alarmManager，每拿到一个时间点就新建一个intent，然后用alarmManager
    // 启动一个intentservice；（把questionnaire的ID传进去）service中直接引用TriggerManager的overview的加问卷方法 添加到触发问卷。


    private void addTriggeredByTimeQuestionnaire() {
        for (Questionnaire questionnaire : allQuestionnaireList) {
            if (questionnaire.getTriggerEvent().getTime() != null) {
                this.triggeredByTimeQuestionnaireList.add(questionnaire);
            }
        }
    }

    private void addTriggeredByDatetimeQuestionnaire() {
        for (Questionnaire questionnaire : allQuestionnaireList) {
            if (questionnaire.getTriggerEvent().getDateTime() != null) {
                this.triggeredByDateQuestionnaireList.add(questionnaire);
            }
        }
    }

    private void setEveryTriggeredTimeQuestionnaireAlarm(AlarmManager alarmManager) {
        AlarmManager alarmManager1 = alarmManager;
        int index = 0;
        long oneDay = 1000 * 60 * 60 * 24;
        for (Questionnaire questionnaire : triggeredByTimeQuestionnaireList) {
            //格式是否正确？
            long triggeredTime = this.transferTriggeredTimeToTriggerAtMillis(questionnaire
                                                                                     .getTriggerEvent()
                                                                                     .getTime());
            Intent intent = new Intent("addQuestionnaireToList");
            //intent.setAction("addQuestionnaireToList");
            Log.d(TAG, intent.getAction());
            intent.putExtra("questionnaireID",
                            questionnaire.getTriggerEvent().getQuestionnaireID());
            Log.d(TAG, intent.getStringExtra("questionnaireID"));
            PendingIntent sender = PendingIntent.getBroadcast(OverviewActivity.this, index, intent,
                                                              PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, triggeredTime, oneDay, sender);
            index++;
            Log.d(TAG, String.valueOf(index));
        }

    }

    private void setEveryTriggeredDateQuestionnaireAlarm(AlarmManager alarmManager) {
        AlarmManager alarmManager1 = alarmManager;
        int index = 0;
        for (Questionnaire questionnaire : triggeredByDateQuestionnaireList) {
            //格式是否正确？
            long triggeredtDate = questionnaire.getTriggerEvent().getDateTime().getTime();

            if (triggeredtDate >= TimeUtil.getCurrentTime().getTime()) {
                Log.d(TAG, String.valueOf(triggeredtDate - TimeUtil.getCurrentTime().getTime()));
                //要不要判断时间点是否已经过去
                Intent intent = new Intent("addQuestionnaireToList");
                //intent.setAction("addQuestionnaireToList");
                Log.d(TAG, intent.getAction());
                intent.putExtra("questionnaireID",
                                questionnaire.getTriggerEvent().getQuestionnaireID());
                Log.d(TAG, intent.getStringExtra("questionnaireID"));
                PendingIntent sender = PendingIntent
                        .getBroadcast(OverviewActivity.this, index, intent,
                                      PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, triggeredtDate, sender);
                index++;
                Log.d(TAG, String.valueOf(index));

            } else {
                Log.d(TAG, "Sorry,time is out,the questionnaire will not be added");
            }

        }

    }

    private long transferTriggeredTimeToTriggerAtMillis(String time) {
        long timetest;
        boolean todayshouldtriggered = false;
        String inputTime = time;
        String[] inputTimeList = inputTime.split("-");
        Integer[] inputTimeListOfInteger = new Integer[inputTimeList.length];
        for (int i = 0; i < inputTimeList.length; i++) {
            inputTimeListOfInteger[i] = Integer.valueOf(inputTimeList[i]);
            //System.out.println(String.valueOf(time2[i]));
        }
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
        Calendar calendar1 = Calendar.getInstance();
        Log.i(TAG, calendar1.getTimeZone().toString());
        //calendar1.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        Date currentDate = calendar1.getTime();
        DateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        String currentTime = sdf.format(currentDate);
        String[] currentTimeList = currentTime.split("-");
        Integer[] currentTimeListOfInteger = new Integer[currentTimeList.length];
        for (int i = 0; i < currentTimeList.length; i++) {
            currentTimeListOfInteger[i] = Integer.valueOf(currentTimeList[i]);
            //System.out.println(String.valueOf(time4[i]));
        }
        long setTime = inputTimeListOfInteger[0] * 60 * 60 + inputTimeListOfInteger[1] * 60 +
                inputTimeListOfInteger[2];
        long nowTime = currentTimeListOfInteger[0] * 60 * 60 + currentTimeListOfInteger[1] * 60 +
                currentTimeListOfInteger[2];
        Log.i(TAG, String.valueOf(setTime - nowTime));
        if (setTime >= nowTime) {
            todayshouldtriggered = true;
        }
        //============test==============
        if (todayshouldtriggered) {
            Log.i(TAG, "will add a questionnaire");
        } else {
            Log.i(TAG, "will Tomorrow add a questionnaire");
        }

        if (todayshouldtriggered) {
            calendar1.set(Calendar.HOUR_OF_DAY, inputTimeListOfInteger[0]);
            calendar1.set(Calendar.MINUTE, inputTimeListOfInteger[1]);
            calendar1.set(Calendar.SECOND, inputTimeListOfInteger[2]);
        } else {
            calendar1.add(Calendar.DAY_OF_MONTH, 1);
            calendar1.set(Calendar.HOUR_OF_DAY, inputTimeListOfInteger[0]);
            calendar1.set(Calendar.MINUTE, inputTimeListOfInteger[1]);
            calendar1.set(Calendar.SECOND, inputTimeListOfInteger[2]);
        }
        return calendar1.getTime().getTime();

        //DateFormat sdf3 = new SimpleDateFormat("yyyy MM dd HH mm ss");
        //String s=sdf3.format(date);


    }


}
