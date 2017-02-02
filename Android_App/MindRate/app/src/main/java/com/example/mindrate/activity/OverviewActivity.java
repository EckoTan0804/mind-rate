package com.example.mindrate.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.fragment.AboutUsFragment;
import com.example.mindrate.fragment.ChooseQuestionnaireFragment;
import com.example.mindrate.fragment.ProbandProfileFragment;
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
import com.example.mindrate.util.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OverviewActivity extends BaseActivity {

    private Proband proband;
    private List<Questionnaire> questionnaireList;

    // ==================== View components ==================================
    private DrawerLayout mDrawerLayout;
    private Button btn_nav;
    private NavigationView navView;
    private TextView tv_title;
    private SensorManager sM;
    private List<Sensor> allSensors;
    //    private TriggerEventManager tEM;

    // =======================================================================

    ProbandProfileFragment probandProfileFragment = new ProbandProfileFragment();
    ChooseQuestionnaireFragment chooseQuestionnaireFragment = new ChooseQuestionnaireFragment();
    AboutUsFragment aboutUsFragment = new AboutUsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        initFromIntent();
        initView();
        initTestData();
        sM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        allSensors = sM.getSensorList(Sensor.TYPE_ALL);
        //        tEM =  new TriggerEventManager();

        //        tv_questionText.setText(Utility.createJSON(this.proband));
    }

    private void initFromIntent() {
        Intent intent = getIntent();

        // questionnaires
        String questionnaireJSON = intent.getStringExtra("questionnaire_JSON");
        this.questionnaireList = Utility.fromJsonToQuestionnaire(questionnaireJSON);

        // proband
        this.proband = (Proband) intent.getParcelableExtra("proband");
    }

    private void initView() {

        // =================== TextView title ===============================
        tv_title = (TextView) findViewById(R.id.title_title);
        // ==================================================================

        tv_title.setText("Mind Rate");
        replaceFragment(new WelcomeFragment());

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
                        // TODO: replace Settings-Fragment
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
                iterator.remove();
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
                    removeQuestionnaire(answeredQuestionnaireID);


                }
                break;
            default:

                break;
        }
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


        questionnaireList.add(questionnaire);
        questionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        questionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));
    }
}
