package com.example.mindrate.util;

import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.Duration;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static com.example.mindrate.util.JsonUtil.fromJsonToQuestionnaireList;
import static org.junit.Assert.assertTrue;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/18:11:02</br>
 * </p>
 */
public class JsonUtilTest {

    private Gson gson;
    private JsonParser jsonParser;
    private Questionnaire testQuestionnaire;

    @Before
    public void setUp() throws Exception {
        gson = JsonUtil.getGson();
        jsonParser = new JsonParser();
        testQuestionnaire = initQuestionnaireTestData();
    }

    @After
    public void tearDown() throws Exception {
        gson = null;
        jsonParser = null;
        testQuestionnaire = null;
    }

    @Ignore
    public void createJSON() throws Exception {
        /**
         * Since this method only call gson.toJson() and toJson() is a method from gson,
         * so there is no need to test.
         */
    }

    @Ignore
    public void fromJsonToProband() throws Exception {
        /**
         * Since this method only call gson.fromJson() and fromJson() is a method from gson,
         * so there is no need to test.
         */
    }

    @Ignore
    public void getGson() throws Exception {

    }

    @Test
    public void testFromJsonToQuestionList() throws Exception {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(new SingleChoice()));
        questions.add(new Question(new MultipleChoice()));
        questions.add(new Question(new StepScale()));
        questions.add(new Question(new DragScale()));
        questions.add(new Question(new TextAnswer()));

        String json = gson.toJson(questions);
        JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
        ArrayList<Question> questionArrayList = JsonUtil.fromJsonToQuestionList(jsonArray);

        assertTrue(questionArrayList.get(0).getQuestionType() instanceof SingleChoice);
        assertTrue(questionArrayList.get(1).getQuestionType() instanceof MultipleChoice);
        assertTrue(questionArrayList.get(2).getQuestionType() instanceof StepScale);
        assertTrue(questionArrayList.get(3).getQuestionType() instanceof DragScale);
        assertTrue(questionArrayList.get(4).getQuestionType() instanceof TextAnswer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromJsonToQuestionnaireList() {
        fromJsonToQuestionnaireList(null);
        fromJsonToQuestionnaireList("");
    }

    @Test
    public void testFromJsonToQuestionnaire() throws Exception {
        String json = gson.toJson(testQuestionnaire);
        JsonElement jsonElement = jsonParser.parse(json);
        Questionnaire questionnaire = JsonUtil.fromJsonToQuestionnaire(jsonElement);
//        assertEquals(testQuestionnaire, questionnaire);
        assertTrue(questionnaire.equals(testQuestionnaire));
    }


    public Questionnaire initQuestionnaireTestData(){
        Questionnaire questionnaireA = new Questionnaire("A", new Duration(48, 0, 0));
        questionnaireA.setStudyID("1");
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
                               ""));
        options.add(new Option("bad",
                               ""));
        options.add(new Option("so so",
                               ""));
        options.add(new Option("good",
                               ""));
        options.add(new Option("very good!",
                               ""));
        Question q5 = new Question("Do you like this app?",
                                   new StepScale(options),
                                   "Q5", true);
        questionnaireA.addQuestion(q5);

//        TriggerEvent triggerEvent1 = new TriggerEvent(questionnaireA.getQuestionnaireID());
//        triggerEvent1.setLight(true);
//        triggerEvent1.setLightMinValue(1000);
//        triggerEvent1.setLightMaxValue(2000);
//        triggerEvent1.setMinTimeSpace(5);
//        triggerEvent1.setAirTemperature(true);
//        triggerEvent1.setAmbientTemperatureMaxValue(20);
//        triggerEvent1.setAmbientTemperatureMinValue(19);
//        questionnaireA.setTriggerEvent(triggerEvent1);

        // q6
        Question q6 = new Question("Will you recommand our app to your friend?", new DragScale
                (10), "Q6", true);
        questionnaireA.addQuestion(q6);

        return questionnaireA;
    }


}