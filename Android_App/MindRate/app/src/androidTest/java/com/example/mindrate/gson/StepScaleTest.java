package com.example.mindrate.gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/19:00:27</br>
 * </p>
 */
public class StepScaleTest {
    private StepScale singleChoice;
    private StepScale test;

    @Before
    public void setUp() throws Exception {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("A", "1"));
        options.add(new Option("B", "2"));
        options.add(new Option("C", "3"));
        singleChoice = new StepScale(options);
    }

    @After
    public void tearDown() throws Exception {
        singleChoice = null;
        test = null;
    }

    @Test
    public void equals() throws Exception {
        // null object
        assertFalse(singleChoice.equals(null));

        // different optionList's size
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(new Option("A", "1"));
        optionArrayList.add(new Option("B", "2"));
        //        options.add(new Option("C", "3"));
        test = new StepScale(optionArrayList);
        assertFalse(singleChoice.equals(test));

        // same optionList's size but element is not equal
        optionArrayList.add(new Option("C", "4"));
        assertFalse(singleChoice.equals(test));

        // same size with every element equal
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("A", "1"));
        optionList.add(new Option("B", "2"));
        optionList.add(new Option("C", "3"));
        test = new StepScale(optionList);
        assertTrue(singleChoice.equals(test));
    }
}