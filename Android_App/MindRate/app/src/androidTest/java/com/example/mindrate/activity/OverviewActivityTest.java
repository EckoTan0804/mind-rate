package com.example.mindrate.activity;

import android.content.Intent;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mindrate.R;
import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.Duration;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.Researcher;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.Study;
import com.example.mindrate.gson.TextAnswer;
import com.example.mindrate.gson.TriggerEvent;
import com.example.mindrate.util.JsonUtil;
import com.example.mindrate.util.TimeUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Renhan on 2017/3/19.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class OverviewActivityTest  {

    @Rule
    public ActivityTestRule<OverviewActivity> overviewActivityRule = new ActivityTestRule<>
            (OverviewActivity.class, false, false);

    private OverviewActivity overviewActivity;

    @Before
    public void setUp() throws Exception {

        Intent intent = new Intent();
        intent.putExtra("proband", new Proband("1", "123"));
        intent.putExtra("questionnaire_JSON", initTestJson());

        overviewActivity = overviewActivityRule.launchActivity(intent);
    }

    @After
    public void tearDown() throws Exception {
        overviewActivity.finish();
    }

    @Test
    public void testPreCondition() {
        onView(withText(R.string.hello)).check(matches(isDisplayed()));
    }

    @Test
    public void testDrawerLayout() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
    }

    @Test
    public void testNavigationView() {

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));

        // check whether the nav items are displayed
        onView(withText(R.string.nav_profile)).check(matches(isDisplayed()));
        onView(withText(R.string.nav_questionnaire)).check(matches(isDisplayed()));
        onView(withText(R.string.nav_about_us)).check(matches(isDisplayed()));
        onView(withText(R.string.nav_settings)).check(matches(isDisplayed()));

        // click nav item

        // profile
        onView(withText(R.string.nav_profile)).perform(click());
        onView(withId(R.id.title_title)).check(matches(withText(R.string.nav_profile)));
        onView(withText(R.string.log_in_study_id)).check(matches(isDisplayed()));
        onView(withText("1")).check(matches(isDisplayed()));
        onView(withText(R.string.log_in_proband_id)).check(matches(isDisplayed()));
        onView(withText("123")).check(matches(isDisplayed()));

        // questionnaire
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
        onView(withText(R.string.nav_questionnaire)).perform(click());
        onView(withId(R.id.title_title)).check(matches(withText(R.string.nav_questionnaire)));

        // about us
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
        onView(withText(R.string.nav_about_us)).perform(click());
        onView(withId(R.id.title_title)).check(matches(withText(R.string.nav_about_us)));
        onView(withText(R.string.developer)).check(matches(isDisplayed()));
        onView(withText(" Shanshan Du \n Yi Ge \n Renhan Lou \n Ruoheng Ma \n Haobin Tan")).check
                (matches(isDisplayed()));

        // settings
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(matches(isDisplayed()));
        onView(withText(R.string.nav_settings)).perform(click());
        onView(withId(R.id.title_title)).check(matches(withText(R.string.nav_settings)));
//        onView(withText(R.string.language)).check(matches(isDisplayed()));

    }

    @Ignore
    private String initTestJson() {

        List<Questionnaire> questionnaireList = new ArrayList<>();

        Questionnaire questionnaireA = new Questionnaire("1", "A", new Duration(48, 0, 0));
        questionnaireA.setTriggerTime(TimeUtil.getCurrentTime());
        // q1
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("happy",
                                  "Q2"));
        optionList.add(new Option("unhappy",
                                  "Q3"));
        Question q1 = new Question("Are you happy?",
                                   new SingleChoice(optionList),
                                   "Q1", true);
        questionnaireA.addQuestion(q1);

        // q2
        Question q2 = new Question("Why happy?",
                                   new TextAnswer(),
                                   "Q2", false);
        questionnaireA.addQuestion(q2);

        // q3
        Question q3 = new Question("Why unhappy?",
                                   new TextAnswer(),
                                   "Q3", false);
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
                                   "Q4", true);
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
                                   "Q5", true);
        questionnaireA.addQuestion(q5);

        TriggerEvent triggerEvent1 = new TriggerEvent(questionnaireA.getQuestionnaireID());
        triggerEvent1.setLight(true);
        triggerEvent1.setLightMinValue(1000);
        triggerEvent1.setLightMaxValue(2000);
        triggerEvent1.setMinTimeSpace(5);
        triggerEvent1.setAirTemperature(true);
        triggerEvent1.setAmbientTemperatureMaxValue(20);
        triggerEvent1.setAmbientTemperatureMinValue(19);
        questionnaireA.setTriggerEvent(triggerEvent1);


        // q6
        Question q6 = new Question("Will you recommand our app to your friend?", new DragScale
                (10), "Q6", true);
        questionnaireA.addQuestion(q6);

        questionnaireList.add(questionnaireA);
//        questionnaireList.add(new Questionnaire("B", new Duration(24, 0, 0)));
//        questionnaireList.add(new Questionnaire("C", new Duration(12, 0, 0)));
//        questionnaireList.add(new Questionnaire("D", new Duration(6, 0, 0)));

        return JsonUtil.createJSON(new Researcher(new Study(questionnaireList)));
    }


}