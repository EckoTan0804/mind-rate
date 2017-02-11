package com.example.mindrate.gson;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Renhan on 2017/1/25.
 */

public class TriggerEventManager extends Observable{
    private static final String TAG = "TriggerEventManager";

    //private boolean isQuestionnaireValid;
    private float[][] dataOfAllSensor;


    private List<Questionnaire> shouldAnswerQuestionnaire;
    private List<Questionnaire> questionnaireList;
    private static final int TYPE_ACCELEROMETER = 0;
    private static final int  TYPE_AMBIENT_TEMPERATURE = 1;
    private static final int  TYPE_GRAVITY = 2;
    private static final int  TYPE_GYROSCOPE = 3;
    private static final int  TYPE_LIGHT = 4;
    private static final int  TYPE_LINEAR_ACCELERATION = 5;
    private static final int  TYPE_MAGNETIC_FIELD = 6;
    private static final int  TYPE_ORIENTATION = 7;
    private static final int  TYPE_PRESSURE = 8;
    private static final int  TYPE_PROXIMITY = 9;
    private static final int  TYPE_RELATIVE_HUMIDITY = 10;
    private static final int  TYPE_ROTATION_VECTOR = 11;

    /*public TriggerEventManager(List<Questionnaire> questionnaireList){
        this.questionnaireList = questionnaireList;
        //this.isQuestionnaireValid = false;
        this.dataOfAllSensor = new float[12][];
        this.shouldAnswerQuestionnaire = new ArrayList<>();
    }*/

    private TriggerEventManager(){
        this.questionnaireList = null;
        this.dataOfAllSensor = new float[12][3];
        this.shouldAnswerQuestionnaire = new ArrayList<>();
    }

    private static final TriggerEventManager TRIGGER_EVENT_MANAGER = new TriggerEventManager();

    public static TriggerEventManager getTriggerEventManager(){
        return TRIGGER_EVENT_MANAGER;
    }

    /*public boolean getIsQuestionnaireValid() {
        return isQuestionnaireValid;
    }

    public void setQuestionnaireValid(boolean questionnaireValid) {
        isQuestionnaireValid = questionnaireValid;
    }*/


    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(ArrayList<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }





    public void setDataOfSensor(int index,float[] dataOfSensor){
        this.dataOfAllSensor[index]=dataOfSensor;
        //setChanged();
        //notifyObservers();
        float[] lightTest1 = this.dataOfAllSensor[4];
        float lightTest = lightTest1[0];
        String a = String.valueOf(lightTest);
        Log.d(TAG,a);
    }

    public void addShouldAnswerQuestionnaire(Questionnaire questionnaire){
        this.shouldAnswerQuestionnaire.add(questionnaire);
    }

    public void addQuestionnaire(Questionnaire questionnaire){
        if(this.questionnaireList !=null){
            this.questionnaireList.add(questionnaire);
        }else{
            this.questionnaireList = new ArrayList<>();
            this.questionnaireList.add(questionnaire);
        }
    }









}
