package com.example.mindrate.activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Renhan on 2017/3/19.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class OverviewActivityTest  {
    @Rule
    public ActivityTestRule<OverviewActivity> overviewActivityRule = new ActivityTestRule<>(OverviewActivity.class);


    @Before
    public void setUp() throws Exception {

       // injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        //mActivity = getActivity();
        //Intent intent = new Intent(Intent.ACTION_MAIN);
        //startActivity(intent);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void onResume() throws Exception {

    }

    @Test
    public void onNewIntent() throws Exception {

    }

    @Test
    public void onDestroy() throws Exception {

    }

    @Test
    public void addQuestionnaireToTriggeredQuestionnaireList() throws Exception {

    }

    @Test
    public void onActivityResult() throws Exception {

    }

    @Test
    public void switchLanguageImmediately() throws Exception {

    }

    @Test
    public void actionBar() throws Exception {

    }

    @Test
    public void getProband() throws Exception {

    }

    @Test
    public void setProband() throws Exception {

    }

    @Test
    public void getAllQuestionnaireList() throws Exception {

    }

    @Test
    public void setAllQuestionnaireList() throws Exception {

    }

    @Test
    public void getSelectedQuestionnaire() throws Exception {

    }

    @Test
    public void setSelectedQuestionnaire() throws Exception {

    }

    @Test
    public void getTriggeredQuestionnaireList() throws Exception {

    }

    @Test
    public void setTriggeredQuestionnaireList() throws Exception {

    }

    @Test
    public void getSelectedQuestionnaireIndex() throws Exception {

    }

    @Test
    public void setSelectedQuestionnaireIndex() throws Exception {

    }

    @Test
    public void getInstance() throws Exception {

    }

}