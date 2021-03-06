package com.example.mindrate.gson;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;

/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.test</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/16:14:43</br>
 * </p>
 */
public class QuestionnaireTest {

    private Questionnaire questionnaire;
    private List<Question> questionList;

    @Before
    public void setUp() throws Exception {
        questionnaire = new Questionnaire();
        questionList = questionnaire.getQuestionList();
        questionList.add(new Question("A", new SingleChoice(), "1", true));
        questionList.add(new Question("B", new MultipleChoice(), "2", false));
        questionList.add(new Question("C", new StepScale(), "3", true));
        questionList.add(new Question("D", new DragScale(), "4", true));
        questionList.add(new Question("E", new TextAnswer(), "5", false));
    }

    @After
    public void tearDown() throws Exception {
        questionnaire = null;
    }

    @Test
    public void sendNotification() throws Exception {

    }

    @Test
    public void addQuestion() throws Exception {
        Question question = new Question("A", new TextAnswer(), "Test", true);
        questionnaire.addQuestion(question);

        MatcherAssert.assertThat(questionList, hasItem(question));

    }

    @Test
    public void getQuestion() throws Exception {
        Assert.assertNull(questionnaire.getQuestion("20"));
        Assert.assertEquals(new Question("A", new SingleChoice(), "1", true),
                            questionnaire.getQuestion("1"));
    }

    @Test
    public void defaultNextQuestionID() throws Exception {
        Question currentQuestion = questionList.get(2);
        Assert.assertEquals("4", questionnaire.defaultNextQuestionID(currentQuestion));
        currentQuestion = questionList.get(3);
        Assert.assertNull(questionnaire.defaultNextQuestionID(currentQuestion));
    }

    @Ignore
    public void trigger() throws Exception {

    }

    @Test
    public void initTriggeredData() throws Exception {
        Assert.assertEquals(-10000, questionnaire.getAmbientTemperatureValue(), 0);
        Assert.assertEquals(-10000, questionnaire.getLightValue(), 0);
        Assert.assertEquals(-10000, questionnaire.getPressureValue(), 0);
        Assert.assertEquals(-10000, questionnaire.getProximityValue(), 0);
        Assert.assertEquals(-10000, questionnaire.getRelativeHumidityValue(), 0);

    }

}