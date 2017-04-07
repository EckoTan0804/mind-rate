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
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Questionnaire;
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

/**
 * This is the fragment where the proband can switch to different fragements:
 * <p><code>AboutUsFragment</code></p>
 * <p><code>ChooseQuestionnaireFragment</code></p>
 * <p><code>ProbandProfileFragment</code></p>
 * <p><code>SettingFragment</code></p>
 * <p><code>WelcomeFragment</code></p>
 * <p>
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.activity</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/13:22:11</br>
 * </p>
 */
public class OverviewActivity extends BaseActivity {

    private static final String TAG = "OverviewActivity";
    private static OverviewActivity instance = null;
    private static boolean needIntent = true;
    //myBroadcastReceiver receiver = new  myBroadcastReceiver();
    //Calendar calendar0 = Calendar.getInstance();
    //private long timeDiff =calendar0.getTimeInMillis()-TimeZone.getDefault().getRawOffset();
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
    //private int indexForDate = 0;
    //private int indexForTime = 0;

    // =======================================================================
    //    WelcomeFragment welcomeFragment = new WelcomeFragment();
    //    ProbandProfileFragment probandProfileFragment = new ProbandProfileFragment();
    //    ChooseQuestionnaireFragment chooseQuestionnaireFragment = new
    // ChooseQuestionnaireFragment();
    //    AboutUsFragment aboutUsFragment = new AboutUsFragment();
    //    SettingFragment settingFragment = new SettingFragment();

    WelcomeFragment welcomeFragment;
    ProbandProfileFragment probandProfileFragment;
    ChooseQuestionnaireFragment chooseQuestionnaireFragment;
    AboutUsFragment aboutUsFragment;
    SettingFragment settingFragment;


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

        initFragment();

        if (this.allQuestionnaireList == null) {
            this.allQuestionnaireList = new ArrayList<>();
        }
        if (this.triggeredQuestionnaireList == null) {
            this.triggeredQuestionnaireList = new ArrayList<>();
        }

        if (needIntent) {
            initFromIntent();
            needIntent = false;
        }

        initView();

        //        initTestData();
        addTriggeredByTimeQuestionnaire();
        addTriggeredByDatetimeQuestionnaire();

        Log.i(TAG, "TEM created in Activity");
        instance = this;


    }

    /**
     * Initialize fragments
     */
    private void initFragment() {
        welcomeFragment = new WelcomeFragment();
        probandProfileFragment = new ProbandProfileFragment();
        chooseQuestionnaireFragment = new ChooseQuestionnaireFragment();
        aboutUsFragment = new AboutUsFragment();
        settingFragment = new SettingFragment();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        triggerEventManager = TriggerEventManager.getTriggerEventManager();
        triggerEventManager.setOverviewActivity(instance);

        //=============================
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setEveryTriggeredDateQuestionnaireAlarm(alarmManager);
        setEveryTriggeredTimeQuestionnaireAlarm(alarmManager);

        Intent intent = getIntent();
        String fromIntent = intent.getStringExtra("notityToAnswer");
        if (!TextUtils.isEmpty(fromIntent)) {
            if (fromIntent.equals("chooseQuestionnaireFragment")) {
                isFirstLoad = false;
                replaceFragment(chooseQuestionnaireFragment);
            }

        }
        Intent startServiceIntent = new Intent(OverviewActivity.this, DeviceSensorService.class);
        //===========stop service===============

        startService(startServiceIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
        Log.i(TAG, "has unregist....");

    }

    /**
     * Initialize data from intent
     */
    private void initFromIntent() {

        Intent intent = getIntent();

        // questionnaires

        String questionnaireJSON = intent.getStringExtra("questionnaire_JSON");
        if (TextUtils.isEmpty(questionnaireJSON)) {
            questionnaireJSON = PreferenceUtil.getString("questionnaireJSON", "");
        }
        this.allQuestionnaireList = JsonUtil.fromJsonToQuestionnaireList(questionnaireJSON);

        // proband info questionnaire
        Questionnaire probandInfoQuestionnaire = JsonUtil.fromJsonToProbandInfoQuestionnaire
                (questionnaireJSON);

        if (probandInfoQuestionnaire != null&&(probandInfoQuestionnaire.getQuestionList().size()
                !=0)) {
            addQuestionnaireToTriggeredQuestionnaireList(probandInfoQuestionnaire);
        }
        //modified bug for nullpointerexception
//        this.allQuestionnaireList.remove(probandInfoQuestionnaire);

        TriggerEventManager.getTriggerEventManager().setQuestionnaireList(allQuestionnaireList);
        for (Questionnaire questionnaire : allQuestionnaireList) {

                TriggerEvent triggerEvent = questionnaire.getTriggerEvent();
                if (triggerEvent.getSensorList() == null) {
                    triggerEvent.setSensorList(new boolean[12]);
                }
                triggerEvent.setSensor();
                TimeZone.setDefault(TimeZone.getTimeZone("GTM+0"));
                triggerEvent.setDate();

                TriggerEventManager.getTriggerEventManager().addObserver(questionnaire);

        }
        //        TriggerEventManager.getTriggerEventManager().setQuestionnaireList
        // (allQuestionnaireList);

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
        //        String fromIntent = intent.getStringExtra("notityToAnswer");
        //        if (!TextUtils.isEmpty(fromIntent)) {
        //            if (fromIntent.equals("chooseQuestionnaireFragment")) {
        //                isFirstLoad = false;
        //                replaceFragment(chooseQuestionnaireFragment);
        //            }
        //
        //        }

    }


    /**
     * Initialize the view of activity
     */
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

    /**
     * replace the current fragment with <code>fragment</code>
     *
     * @param fragment fragment that the proband wants to switch to
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.switch_fragment,
                           fragment);
        transition.addToBackStack(null);
        transition.commit();
    }


    /**
     * Remove the questionnaire whose index is <code>selectedQuestionnaireIndex</code> from
     * <code>triggeredQuestionnaireList</code>
     */
    private void removeSelectedQuestionByIndex() {
        this.triggeredQuestionnaireList.remove(this.selectedQuestionnaireIndex);
    }

    /**
     * Get the questionnaire with id <code>questionnaireId</code>
     *
     * @param questionnaireID target questionnaire's id
     * @return <br>
     * <li>questionnaire instance whose id is <coded>questionnaireID</coded></li>
     * <li>null, if the target questionnaire is not in <code>allQuestionnaireList</code></li>
     */
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


    /**
     * Add questionnaire with id <code>questionnaireID</code> to
     * <code>triggeredQuestionnaireList</code>
     *
     * @param questionnaireID id of the questionnaire that will be added
     */
    public void addQuestionnaireToTriggeredQuestionnaireList(String questionnaireID) {
        Questionnaire questionnaire = getQuestionnaire(questionnaireID);
        this.addQuestionnaireToTriggeredQuestionnaireList(questionnaire);
    }


    /**
     * Add <code>questionnaire</code> to <code>triggeredQuestionnaireList</code>
     *
     * @param questionnaire questionnaire to be added
     */
    private void addQuestionnaireToTriggeredQuestionnaireList(Questionnaire questionnaire) {

        questionnaire.trigger(OverviewActivity.this);
        Questionnaire q = questionnaire.cloneItself();
        //        q.setTriggerTime(TimeUtil.getCurrentTime());

        if (!this.triggeredQuestionnaireList.isEmpty()) {

            this.triggeredQuestionnaireList.add(q);
            if (chooseQuestionnaireFragment.getAdapter() != null) {
                chooseQuestionnaireFragment.getAdapter().notifyDataSetChanged();
            }
        } else {
            this.triggeredQuestionnaireList.add(q);
        }
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

    /**
     * Change app's language to <code>language</code> immediately
     *
     * @param language <br>
     *                 <li>"en" for English</li>
     *                 <li>"de" for Deutsch</li>
     */
    public void switchLanguageImmediately(String language) {
        super.switchLanguage(language);
        finish();
        Intent intent = new Intent(OverviewActivity.this,
                                   OverviewActivity.class);
        startActivity(intent);

    }

    /**
     * Get actionBar
     *
     * @return <code>title</code>
     */
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


    public static boolean isNeedIntent() {
        return needIntent;
    }

    public static void setNeedIntent(boolean needIntent) {
        OverviewActivity.needIntent = needIntent;
    }


    /**
     * Helper method.Gets instance for class overviewActivity.
     *
     * @return the instance,overviewActivity itself.
     */
    public static OverviewActivity getInstance() {
        return instance;
    }


    /**
     * Select questionnaire,which will everyday triggered by Time.
     */
    private void addTriggeredByTimeQuestionnaire() {
        for (Questionnaire questionnaire : allQuestionnaireList) {
            if (questionnaire.getTriggerEvent().getTime() != null) {
                this.triggeredByTimeQuestionnaireList.add(questionnaire);
            }
        }
    }

    /**
     * Select questionnaire,which will triggered by Date.
     */
    private void addTriggeredByDatetimeQuestionnaire() {
        for (Questionnaire questionnaire : allQuestionnaireList) {
            if (questionnaire.getTriggerEvent().getDateTime() != null) {
                this.triggeredByDateQuestionnaireList.add(questionnaire);
            }
        }
    }

    /**
     * Helper method for each Questionnaire, which will everyday triggered by Time.
     * <br>For these Questionnaire a alarm will be set.</br>
     *
     * @param alarmManager This class provides access to the system alarm services.
     */
    private void setEveryTriggeredTimeQuestionnaireAlarm(AlarmManager alarmManager) {
        AlarmManager alarmManager1 = alarmManager;
        /*if(indexForTime<=indexForDate){
            indexForTime = indexForDate+1;
        }*/
        int index = 0;
        long oneDay = 1000 * 60 * 60 * 24;
        for (Questionnaire questionnaire : triggeredByTimeQuestionnaireList) {
            long triggeredTime = this.transferTriggeredTimeToTriggerAtMillis(questionnaire
                                                                                     .getTriggerEvent()
                                                                                     .getTime());
            Intent intent = new Intent("addQuestionnaireToList");
            //intent.setAction("addQuestionnaireToList");
            Log.i(TAG, "Time "+intent.getAction());
            intent.putExtra("questionnaireID",
                            questionnaire.getQuestionnaireID());
           Log.i(TAG, "Time "+intent.getStringExtra("questionnaireID"));
            PendingIntent sender = PendingIntent.getBroadcast(OverviewActivity.this, index, intent,
                                                              PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, triggeredTime, oneDay, sender);
            //indexForTime++;
            index++;
           Log.d(TAG, "Time index "+String.valueOf(index));
        }

    }

    /**
     * Helper method for each Questionnaire, which will triggered by Date.
     * <br>For these Questionnaire a alarm will be set.</br>
     *
     * @param alarmManager This class provides access to the system alarm services.
     */

    private void setEveryTriggeredDateQuestionnaireAlarm(AlarmManager alarmManager) {
        AlarmManager alarmManager1 = alarmManager;
        int index = 10000;
        /*if(indexForDate<=indexForTime){
            indexForDate = indexForTime+1;
        }*/
        for (Questionnaire questionnaire : triggeredByDateQuestionnaireList) {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
            Calendar calendar1 = Calendar.getInstance();
            Date currentDate = calendar1.getTime();
            long triggeredtDate = questionnaire.getTriggerEvent().getDateTime().getTime();

            if (triggeredtDate >= TimeUtil.getCurrentTime().getTime()) {
                Log.i(TAG, "Date "+String.valueOf((triggeredtDate - currentDate.getTime())/1000));
                Intent intent = new Intent("addQuestionnaireToList");
                //intent.setAction("addQuestionnaireToList");
                Log.i(TAG, "Date "+intent.getAction());
                intent.putExtra("questionnaireID",
                                questionnaire.getQuestionnaireID());
                Log.i(TAG, "Date "+intent.getStringExtra("questionnaireID"));
                PendingIntent sender = PendingIntent
                        .getBroadcast(OverviewActivity.this, index, intent,
                                      PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, triggeredtDate, sender);
               // indexForDate++;
                index++;
                Log.i(TAG, "Date index "+String.valueOf(index));

            } else {
                Log.i(TAG, "Date "+"Sorry,time is out,the questionnaire will not be added");
            }

        }

    }

    /**
     * Helper Method.For Method setEveryTriggeredTimeQuestionnaireAlarm.
     * <br>Input time is as string,output time is as long value.</br>
     * <p>
     *
     * @param time Questionnaire's triggered time
     * @return time as a long value.
     */
    private long transferTriggeredTimeToTriggerAtMillis(String time) {
        long timetest;
        boolean todayshouldtriggered = false;
        String inputTime = time;
        String[] inputTimeList = inputTime.split("-");
        Integer[] inputTimeListOfInteger = new Integer[inputTimeList.length];
        for (int i = 0; i < inputTimeList.length; i++) {
            inputTimeListOfInteger[i] = Integer.valueOf(inputTimeList[i].trim());
            //System.out.println(String.valueOf(time2[i]));
        }
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
        Calendar calendar1 = Calendar.getInstance();
        Log.i(TAG, "Time "+calendar1.getTimeZone().toString());
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
        Log.i(TAG, "Time "+String.valueOf(setTime - nowTime)+" settime");
        if (setTime >= nowTime) {
            todayshouldtriggered = true;
        }
        //============test==============
        if (todayshouldtriggered) {
            Log.i(TAG, "Time "+"will add a questionnaire");
        } else {
            Log.i(TAG, "Time "+"will Tomorrow add a questionnaire");
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


    }


}
