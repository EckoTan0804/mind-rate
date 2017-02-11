package com.example.mindrate.util;


import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Questionnaire;
import com.google.gson.Gson;

import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.util
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:41
 */

public class JsonUtil {

    public static Questionnaire handleJsonObject() {
        return null;
    }

    public static String createJSON(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static List<Questionnaire> fromJsonToQuestionnaire(String json) {
        // TODO: parse json
        return null;
    }

    public static Proband fromJsonToProband(String probandJSON) {
        Gson gson = new Gson();
        Proband proband = gson.fromJson(probandJSON, Proband.class);
        return proband;
    }
}
