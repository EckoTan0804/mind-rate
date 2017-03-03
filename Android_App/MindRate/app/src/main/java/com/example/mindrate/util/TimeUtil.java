package com.example.mindrate.util;


import com.example.mindrate.gson.Duration;

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

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM" +
                                                                          ".dd, HH:mm:ss ");

    public static Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static Date calculateTime(Date beginDate, Duration duration) {
        return new Date(beginDate.getTime() + (long) (duration.getHour() * 60 * 60 + duration
                .getMinute() * 60 + duration.getSecond()) *
                1000);

    }

    public static String parseDate(Date date) {
        return format.format(date);
    }

}
