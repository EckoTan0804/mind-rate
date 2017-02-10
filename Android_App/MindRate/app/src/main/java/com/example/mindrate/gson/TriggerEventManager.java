package com.example.mindrate.gson;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Renhan on 2017/1/25.
 */

public class TriggerEventManager extends Observable{
    //private boolean isQuestionnaireValid;
    private float[][] dataOfAllSensor;


    private ArrayList<Questionnaire> shouldAnswerQuestionnaire;
    private ArrayList<Questionnaire> questionnaireList;
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

    public TriggerEventManager(ArrayList questionnaireList){
        this.questionnaireList = questionnaireList;
        //this.isQuestionnaireValid = false;
        this.dataOfAllSensor = new float[12][];
        this.shouldAnswerQuestionnaire = new ArrayList<>();
    }

    /*public boolean getIsQuestionnaireValid() {
        return isQuestionnaireValid;
    }

    public void setQuestionnaireValid(boolean questionnaireValid) {
        isQuestionnaireValid = questionnaireValid;
    }*/


    public ArrayList<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(ArrayList<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }


    public void setDataOfSensor(int index,float[] dataOfSensor){
        this.dataOfAllSensor[index]=dataOfSensor;
        setChanged();
        notifyObservers();

    }

    public void addShouldAnswerQuestionnaire(Questionnaire questionnaire){
        this.shouldAnswerQuestionnaire.add(questionnaire);
    }







}
