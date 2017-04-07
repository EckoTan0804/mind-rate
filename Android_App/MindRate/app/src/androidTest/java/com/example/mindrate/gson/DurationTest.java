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
 * <br>Created at 2017/3/18:22:09</br>
 * </p>
 */
public class DurationTest {

    private Duration duration;

    @Before
    public void setUp() throws Exception {
        duration = new Duration(20, 20, 20);
    }

    @After
    public void tearDown() throws Exception {
        duration = null;
    }

    @Test
    public void equals() throws Exception {
        assertFalse(duration.equals(new Question()));
        assertFalse(duration.equals(null));
        assertFalse(duration.equals(new Duration(20, 20, 19)));
        assertTrue(duration.equals(new Duration(20, 20, 20)));

    }

}