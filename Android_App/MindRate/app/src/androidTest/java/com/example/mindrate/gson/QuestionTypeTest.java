package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/18:23:23</br>
 * </p>
 */
public class QuestionTypeTest {

    private QuestionType questionType;

    @Before
    public void setUp() throws Exception {
        questionType = new QuestionType("SingleChoice");
    }

    @After
    public void tearDown() throws Exception {
        questionType = null;
    }

    @Test
    public void equals() throws Exception {
        assertFalse(questionType.equals(null));
        assertFalse(questionType.equals(new QuestionType("MultipleChoice")));
        assertFalse(questionType.equals(new QuestionType("")));
        assertTrue(questionType.equals(new QuestionType("SingleChoice")));
    }

}