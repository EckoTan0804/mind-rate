package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/18:22:45</br>
 * </p>
 */
public class QuestionTest {

    private Question question;

    @Before
    public void setUp() throws Exception {
        question = new Question("Hello World", new SingleChoice(), "1", false);
    }

    @After
    public void tearDown() throws Exception {
        question = null;
    }

    @Test(expected = NullPointerException.class)
    public void equals() throws Exception {
        assertFalse(question.equals(null));
        assertFalse(question.equals(new Question()));
        assertFalse(question.equals(new Question("Hello World", new MultipleChoice(), "1", false)));
        assertFalse(question.equals(new Question("Hi World", new SingleChoice(), "1", false)));
        assertFalse(question.equals(new Question("Hello World", new SingleChoice(), "2", false)));
        assertFalse(question.equals(new Question("Hello World", new SingleChoice(), "1", true)));
        assertTrue(question.equals(new Question("Hello World", new SingleChoice(), "1", false)));

    }

}