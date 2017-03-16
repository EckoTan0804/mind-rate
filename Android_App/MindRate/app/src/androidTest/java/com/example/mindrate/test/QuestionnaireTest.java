package com.example.mindrate.test;

import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.TextAnswer;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;

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

    @Before
    public void setUp() throws Exception {
        questionnaire = new Questionnaire();
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
        Question question = new Question("A", new TextAnswer(), "1", true);
        questionnaire.addQuestion(question);

        MatcherAssert.assertThat(questionnaire.getQuestionList(), contains(question));

    }

    @Test
    public void getQuestion() throws Exception {

    }

    @Test
    public void defaultNextQuestionID() throws Exception {

    }

    @Test
    public void trigger() throws Exception {

    }

}