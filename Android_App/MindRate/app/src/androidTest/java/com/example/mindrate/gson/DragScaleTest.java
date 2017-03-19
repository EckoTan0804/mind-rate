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
 * <br>Created at 2017/3/19:00:28</br>
 * </p>
 */
public class DragScaleTest {

    private DragScale dragScale;
    @Before
    public void setUp() throws Exception {
        dragScale = new DragScale(10);
    }

    @After
    public void tearDown() throws Exception {
        dragScale = null;
    }

    @Test
    public void equals() throws Exception {
        //  null object
        assertFalse(dragScale.equals(null));

        // different max value
        assertFalse(dragScale.equals(new DragScale(9)));

        // same max value
        assertTrue(dragScale.equals(new DragScale(10)));
    }

}