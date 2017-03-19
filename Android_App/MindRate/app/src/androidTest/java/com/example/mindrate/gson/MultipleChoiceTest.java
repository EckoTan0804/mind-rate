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
 * <br>Created at 2017/3/19:00:23</br>
 * </p>
 */
public class MultipleChoiceTest {
    private MultipleChoice singleChoice;
    private MultipleChoice test;

    @Before
    public void setUp() throws Exception {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("A", ""));
        options.add(new Option("B", ""));
        options.add(new Option("C", ""));
        singleChoice = new MultipleChoice(options);
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
        optionArrayList.add(new Option("A", ""));
        optionArrayList.add(new Option("B", ""));
        //        options.add(new Option("C", "3"));
        test = new MultipleChoice(optionArrayList);
        assertFalse(singleChoice.equals(test));

        // same optionList's size but element is not equal
        optionArrayList.add(new Option("D", ""));
        assertFalse(singleChoice.equals(test));

        // same size with every element equal
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("A", ""));
        optionList.add(new Option("B", ""));
        optionList.add(new Option("C", ""));
        test = new MultipleChoice(optionList);
        assertTrue(singleChoice.equals(test));
    }

}