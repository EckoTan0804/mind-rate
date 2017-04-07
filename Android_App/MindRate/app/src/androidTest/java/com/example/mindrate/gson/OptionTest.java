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
 * <br>Created at 2017/3/18:22:26</br>
 * </p>
 */
public class OptionTest {

    private Option option;

    @Before
    public void setUp() throws Exception {
        option = new Option("Hello World", "1");
    }

    @After
    public void tearDown() throws Exception {
        option = null;
    }

    @Test
    public void equals() throws Exception {
        assertFalse(option.equals(null));
        assertFalse(option.equals(new Option(null, null)));
        assertFalse(option.equals(new Option("Hello World", null)));
        assertFalse(option.equals(new Option(null, "1")));
        assertTrue(option.equals(new Option("Hello World", "1")));

    }

}