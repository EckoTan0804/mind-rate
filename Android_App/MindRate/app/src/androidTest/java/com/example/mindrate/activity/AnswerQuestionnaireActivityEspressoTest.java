package com.example.mindrate.activity;

import android.content.Intent;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.SeekBar;

import com.example.mindrate.R;
import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.Duration;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.QuestionAnswer;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.QuestionnaireAnswer;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.activity</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/19:13:45</br>
 * </p>
 */
@RunWith(AndroidJUnit4.class)
public class AnswerQuestionnaireActivityEspressoTest {

    private AnswerQuestionnaireActivity answerQuestionnaireActivity;

    @Rule
    public ActivityTestRule<AnswerQuestionnaireActivity> mActivityRule = new ActivityTestRule<>
            (AnswerQuestionnaireActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        intent.putExtra("questionnaire", initTestQuestionnaire());
        intent.putExtra("probandID", "123");
        answerQuestionnaireActivity = mActivityRule.launchActivity(intent);
    }

    @After
    public void tearDown() throws Exception {
        answerQuestionnaireActivity.finish();
    }

    @Test
    public void precondition() {

        // expected UI components show up?
        onView(withId(R.id.title_questionnaireID)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_answer_questionnaire)).check(matches(isDisplayed()));
        onView(withId(R.id.display_answer_option)).check(matches(isDisplayed()));
        onView(withId(R.id.next_or_submit)).check(matches(isDisplayed()));
        onView(withId(R.id.questionContent)).check(matches(isDisplayed()));

        // is components' content correct?
        onView(withId(R.id.questionContent)).check(matches(withText("Are you happy?")));
    }

    @Test
    public void ensureAnswerQuestionnaireProcedureWorks() {
        simulateAnswerQuestionnaire();
    }

    @Test
    public void ensureRecordAnswerWorks() {

        simulateAnswerQuestionnaire();

        QuestionnaireAnswer test = new QuestionnaireAnswer("A", "123");
        test.getQuestionAnswerList().add(new QuestionAnswer("Q1", "happy"));
        test.getQuestionAnswerList().add(new QuestionAnswer("Q2", "I don't know"));
        test.getQuestionAnswerList().add(new QuestionAnswer("Q4", "[Coding, Studying]"));
        test.getQuestionAnswerList().add(new QuestionAnswer("Q5", "very good!"));
        test.getQuestionAnswerList().add(new QuestionAnswer("Q6", "8"));

        assertEquals(answerQuestionnaireActivity.getQuestionnaireAnswer(), test);

    }

    @Ignore
    private void simulateAnswerQuestionnaire() {
        // q1
        onView(withText("happy")).perform(click());
        onView(withId(R.id.next_or_submit)).perform(click());

        // q2
        onView(withId(R.id.questionContent)).check(matches(withText("Why happy?")));
        onView(withHint(R.string.edit_text_hint)).perform(typeText("I don't know"),
                                                          closeSoftKeyboard());
        onView(withId(R.id.next_or_submit)).perform(click());

        // q4
        onView(withId(R.id.questionContent)).check(matches(withText("What's ur hobby?")));
        onView(withText("Coding")).perform(click());
        onView(withText("Studying")).perform(click());
        onView(withId(R.id.next_or_submit)).perform(click());

        // q5
        onView(withId(R.id.questionContent)).check(matches(withText("Do you like this app?")));
        onView(withText("very good!")).perform(click());
        onView(withId(R.id.next_or_submit)).perform(click());

        // q6
        onView(withId(R.id.questionContent)).check(matches(withText("Will you recommand our app to your friend?")));
        onView(withClassName(Matchers.equalTo(SeekBar.class.getName()))).perform(setProgress(8));
        onView(withId(R.id.next_or_submit)).perform(click());

        // finish
        onView(withId(R.id.questionContent)).check(matches(withText(R.string.finish)));
    }

    @Ignore
    private Questionnaire initTestQuestionnaire() {
        Questionnaire questionnaireA = new Questionnaire("A", new Duration(48, 0, 0));
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
                                       ""));
        optionArrayList.add(new Option("Reading",
                                       ""));
        optionArrayList.add(new Option("Coding",
                                       ""));
        optionArrayList.add(new Option("Studying",
                                       ""));
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

        // q6
        Question q6 = new Question("Will you recommand our app to your friend?", new DragScale
                (10), "Q6", true);
        questionnaireA.addQuestion(q6);

        return questionnaireA;
    }

    // custom ViewAction for seekBar
    @Ignore
    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }

}