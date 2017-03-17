package com.example.mindrate.test;

import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
        Assert.assertEquals(new Question("A", new SingleChoice(), "1", true), questionnaire.getQuestion("1"));
    }

    @Test
    public void defaultNextQuestionID() throws Exception {
        Question currentQuestion = questionList.get(2);
        Assert.assertEquals("4", questionnaire.defaultNextQuestionID(currentQuestion));
        currentQuestion = questionList.get(3);
        Assert.assertNull(questionnaire.defaultNextQuestionID(currentQuestion));
    }

    @Test
    public void trigger() throws Exception {

    }

}