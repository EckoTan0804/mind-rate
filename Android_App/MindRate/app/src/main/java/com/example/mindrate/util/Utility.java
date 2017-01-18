package com.example.mindrate.util;


import com.example.mindrate.gson.Questionnaire;
import com.google.gson.Gson;

/**
 * Project: MindRate
 * Package: com.example.mindrate.util
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:41
 */

public class Utility {

    public static Questionnaire handleJsonObject() {
        return null;
    }

    public static String createJSON(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
