package com.example.mindrate.util;


import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.Duration;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.QuestionType;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;
import com.example.mindrate.gson.TriggerEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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
                    .registerSubtype(SingleChoice.class)
                    .registerSubtype(MultipleChoice.class)
                    .registerSubtype(StepScale.class)
                    .registerSubtype(DragScale.class)
                    .registerSubtype(TextAnswer.class);

    private static Gson gsonPolymorphism = new GsonBuilder().registerTypeAdapterFactory
            (runtimeTypeAdapterFactory).serializeNulls().create();

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setPrettyPrinting().create();

    public static String createJSON(Object obj) {
        return gson.toJson(obj);
    }


    public static Proband fromJsonToProband(String probandJSON) {
        Proband proband = gson.fromJson(probandJSON, Proband.class);
        return proband;
    }

    public static Gson getGson() {
        return gson;
    }

    public static ArrayList<Question> fromJsonToQuestionList(JsonArray jsonArray) {
        ArrayList<Question> questionArrayList = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject obj = (JsonObject) element;
            Question q = gson.fromJson(obj, Question.class);
            JsonObject questionType = obj.getAsJsonObject("questionType");
            String type = questionType.get("type").getAsString();
            switch (type) {
                case "SingleChoice":
                    q.setQuestionType((SingleChoice) gsonPolymorphism.fromJson(questionType, new
                            TypeToken<QuestionType>
                                    () {
                            }
                            .getType()));
                    break;
                case "MultipleChoice":
                    q.setQuestionType((MultipleChoice) gsonPolymorphism.fromJson(questionType, new
                            TypeToken<QuestionType>
                                    () {
                            }
                            .getType()));
                    break;
                case "StepScale":
                    q.setQuestionType((StepScale) gsonPolymorphism.fromJson(questionType, new
                            TypeToken<QuestionType>
                                    () {
                            }
                            .getType()));
                    break;
                case "DragScale":
                    q.setQuestionType((DragScale) gsonPolymorphism.fromJson(questionType, new
                            TypeToken<QuestionType>
                                    () {
                            }
                            .getType()));
                    break;
                case "TextAnswer":
                    q.setQuestionType((TextAnswer) gsonPolymorphism.fromJson(questionType, new
                            TypeToken<QuestionType>
                                    () {
                            }
                            .getType()));
                    break;
                default:

                    break;
            }
            questionArrayList.add(q);
        }
        return questionArrayList;
    }

    public static List<Questionnaire> fromJsonToQuestionnaireList(String json) {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement element : jsonArray) {
            Questionnaire questionnaire = fromJsonToQuestionnaire(element);
            questionnaireList.add(questionnaire);
        }
        return questionnaireList;
    }

    public static Questionnaire fromJsonToQuestionnaire(JsonElement element) {
        JsonObject questionnaireObj = (JsonObject)element;
        Questionnaire questionnaire = gson.fromJson(questionnaireObj, Questionnaire.class);
        JsonArray questionList = questionnaireObj.getAsJsonArray("questions");
        questionnaire.setQuestionList(fromJsonToQuestionList(questionList));
        return questionnaire;
    }


    // test parsing json
    public static List<Questionnaire> initJsonTestData() {

        List<Questionnaire> list = new ArrayList<>();

        Questionnaire questionnaireA = new Questionnaire("A", new Duration(48, 0, 0));
        // q1
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("happy",
                                  "Q2"));
        optionList.add(new Option("unhappy",
                                  "Q3"));
        Question q1 = new Question("Are you happy?",
                                   new SingleChoice(optionList),
                                   "Q1", true);
        questionnaireA.addQuestion(q1);

        // q2
        Question q2 = new Question("Why happy?",
                                   new TextAnswer(),
                                   "Q2", false);
        questionnaireA.addQuestion(q2);

        // q3
        Question q3 = new Question("Why unhappy?",
                                   new TextAnswer(),
                                   "Q3", false);
        questionnaireA.addQuestion(q3);

        // q4
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(new Option("Swimming",
                                       null));
        optionArrayList.add(new Option("Reading",
                                       null));
        optionArrayList.add(new Option("Coding",
                                       null));
        optionArrayList.add(new Option("Studying",
                                       null));
        Question q4 = new Question("What's ur hobby?",
                                   new MultipleChoice(optionArrayList),
                                   "Q4", true);
        questionnaireA.addQuestion(q4);

        // q5
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("very bad",
                               null));
        options.add(new Option("bad",
                               null));
        options.add(new Option("so so",
                               null));
        options.add(new Option("good",
                               null));
        options.add(new Option("very good!",
                               null));
        Question q5 = new Question("Do you like this app?",
                                   new StepScale(options),
                                   "Q5", true);
        questionnaireA.addQuestion(q5);

        TriggerEvent triggerEvent1 = new TriggerEvent(questionnaireA.getQuestionnaireID());
        triggerEvent1.setLight(true);
        triggerEvent1.setLightMinValue(1000);
        triggerEvent1.setLightMaxValue(2000);
        triggerEvent1.setMinTimeSpace(5);
        triggerEvent1.setAirTemperature(true);
        triggerEvent1.setAmbientTemperatureMaxValue(20);
        triggerEvent1.setAmbientTemperatureMinValue(19);
        questionnaireA.setTriggerEvent(triggerEvent1);

        // q6
        Question q6 = new Question("Will you recommand our app to your friend?", new DragScale
                (10), "Q6", true);
        questionnaireA.addQuestion(q6);

        list.add(questionnaireA);
        list.add(new Questionnaire("B", new Duration(24, 0, 0)));
        list.add(new Questionnaire("C", new Duration(12, 0, 0)));
        list.add(new Questionnaire("D", new Duration(6, 0, 0)));
        return list;
    }
}
