package com.example.mindrate.util;


import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.QuestionType;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.util
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:41
 */

public class JsonUtil {

    private static RuntimeTypeAdapterFactory<QuestionType> runtimeTypeAdapterFactory =
            RuntimeTypeAdapterFactory
            .of(QuestionType.class, "type")
            .registerSubtype(SingleChoice.class, "SingleChoice")
            .registerSubtype(MultipleChoice.class, "MultipleChoice")
            .registerSubtype(StepScale.class, "StepScale")
            .registerSubtype(DragScale.class, "DragScale")
            .registerSubtype(TextAnswer.class, "TextAnswer");

    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory
            (runtimeTypeAdapterFactory).serializeNulls().create();

    public static String createJSON(Object obj) {
        return gson.toJson(obj);
    }

    public static List<Questionnaire> fromJsonToQuestionnaireList(String json) {
        List<Questionnaire> questionnaireList = gson.fromJson(json, new
                TypeToken<List<Questionnaire>>() {
                }.getType());
        return questionnaireList;
    }

    public static Proband fromJsonToProband(String probandJSON) {
        Proband proband = gson.fromJson(probandJSON, Proband.class);
        return proband;
    }

    public static Gson getGson() {
        return gson;
    }
}
