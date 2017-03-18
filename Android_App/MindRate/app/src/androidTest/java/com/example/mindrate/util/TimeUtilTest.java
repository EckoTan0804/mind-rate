package com.example.mindrate.util;

import com.example.mindrate.gson.Duration;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/18:13:29</br>
 * </p>
 */
public class TimeUtilTest {

    @Ignore
    public void getCurrentTime() throws Exception {
        /*
            No need to test because it only calls the method from Java's API
         */
    }

    @Test(expected=IllegalArgumentException.class)
    public void calculateTime() throws Exception {
        TimeUtil.calculateTime(null, null);
        TimeUtil.calculateTime(TimeUtil.getCurrentTime(), new Duration(-1, -1, -1));

        Duration duration = new Duration(48, 24, 12);
        Assert.assertEquals(duration.getHour() * 60 * 60 + duration.getMinute() * 60 + duration
                .getSecond(), TimeUtil.calculateTime(TimeUtil.getCurrentTime(), duration).getTime());


    }

    @Ignore
    public void parseDate() throws Exception {
        /*
            No need to test because it only calls the method from Java's API
         */
    }

}