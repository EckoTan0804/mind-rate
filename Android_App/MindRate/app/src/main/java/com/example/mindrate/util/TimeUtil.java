package com.example.mindrate.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/14:20:58</br>
 * </p>
 */

public class TimeUtil {

    public static String getCurrentTime() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd, HH:mm:ss ");
        Date currentDate = new Date(System.currentTimeMillis());
        String currentTime = format.format(currentDate);
        return currentTime;
    }
}
