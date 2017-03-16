package com.example.mindrate.gson;

import android.util.Log;

import com.example.mindrate.activity.OverviewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Renhan on 2017/1/25.
 */

public class TriggerEventManager extends Observable{
    private static final String TAG = "TriggerEventManager";
    private OverviewActivity overviewActivity;


    public float[][] getDataOfAllSensor() {
        return dataOfAllSensor;
    }

    //private boolean isQuestionnaireValid;
    private float[][] dataOfAllSensor;


    public List<Questionnaire> getShouldAnswerQuestionnaire() {
        return shouldAnswerQuestionnaire;
    }

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


    private TriggerEventManager(){
        this.questionnaireList = null;
        this.dataOfAllSensor = new float[12][3];
        this.shouldAnswerQuestionnaire = new ArrayList<>();
    }

    private static final TriggerEventManager TRIGGER_EVENT_MANAGER = new TriggerEventManager();

    public static TriggerEventManager getTriggerEventManager(){
        return TRIGGER_EVENT_MANAGER;
    }


    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }




    public void setDataOfSensor(int index,float[] dataOfSensor){
        this.dataOfAllSensor[index]=dataOfSensor;
        Log.d(TAG,"begin notify");
        setChanged();
        notifyObservers();
        Log.d(TAG,"finish notify");
        String sum = String.valueOf(this.shouldAnswerQuestionnaire.size());
        Log.d(TAG,sum);
        float[] lightTest1 = this.dataOfAllSensor[4];
        float[] temtest1 = this.dataOfAllSensor[1];
        float remp = temtest1[0];
        float lightTest = lightTest1[0];
        String a = String.valueOf(lightTest);
        String b = String.valueOf(remp);
        Log.d(TAG,a);
       this.addQuestionnaireToOverviewActivity();

    }

    public void addShouldAnswerQuestionnaire(Questionnaire questionnaire){
        boolean existQuestionnaire = false;
        for (Questionnaire questionnaire1 : this.shouldAnswerQuestionnaire) {
            if (questionnaire1.getQuestionnaireID().equals(questionnaire.getQuestionnaireID())) {
                existQuestionnaire = true;
            }
        }
        if (!existQuestionnaire) {
            this.shouldAnswerQuestionnaire.add(questionnaire);
        }
        //=========test und debug===============
        if(existQuestionnaire){
            Log.i(TAG,"true");
        }else{
            Log.i(TAG,"false");
        }
        //=================================
    }

    public void addQuestionnaire(Questionnaire questionnaire){

        if(this.questionnaireList !=null){
            this.questionnaireList.add(questionnaire);
        }else{
            this.questionnaireList = new ArrayList<>();
            this.questionnaireList.add(questionnaire);
        }
    }

    public void setOverviewActivity(OverviewActivity overviewActivity){
        this.overviewActivity = overviewActivity;//应当写成一个static方法

    }
//
    private void addQuestionnaireToOverviewActivity(){
            for(Questionnaire questionnaire:this.shouldAnswerQuestionnaire) {
                OverviewActivity.getInstance().addQuestionnaireToTriggeredQuestionnaireList
                        (questionnaire.getQuestionnaireID());

                this.shouldAnswerQuestionnaire.remove(questionnaire);

                }

    }



    private void removeQuestionnaireFromShouldList(Questionnaire questionnaire){
        boolean existQuestionnaire =false;
        String questionnaireID = questionnaire.getQuestionnaireID();
        for(Questionnaire questionnaire1:this.shouldAnswerQuestionnaire){
            if(questionnaire1.getQuestionnaireID().equals(questionnaireID)){
                existQuestionnaire = true;
            }

        }
        if(existQuestionnaire){
            int index = this.shouldAnswerQuestionnaire.indexOf(questionnaire);
            this.shouldAnswerQuestionnaire.remove(questionnaire);
        }
    }








}
