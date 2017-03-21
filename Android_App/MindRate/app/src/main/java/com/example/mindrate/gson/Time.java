package com.example.mindrate.gson;


/**
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/3/21:23:15</br>
 * </p>
 */

public class Time {

    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String second;

    public Time(String year, String month, String day, String hour, String minute, String second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}
