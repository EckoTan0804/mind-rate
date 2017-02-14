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

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd, HH:mm:ss ");

    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static Date calculateTime(Date beginDate, int dayDuration) {
        return new Date(beginDate.getTime() + (long)dayDuration * 24 * 60 * 60 * 1000);

    }

    public static String parseDate(Date date) {
        return format.format(date);
    }

}
