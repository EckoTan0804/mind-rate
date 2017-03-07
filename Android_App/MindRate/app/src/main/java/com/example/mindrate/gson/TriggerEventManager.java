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

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }




    public void setDataOfSensor(int index,float[] dataOfSensor){
        this.dataOfAllSensor[index]=dataOfSensor;
        Log.d(TAG,"begin notify");
        setChanged();
        notifyObservers();
        Log.d(TAG,"finish notify");
        //
        String sum = String.valueOf(this.shouldAnswerQuestionnaire.size());
        Log.d(TAG,sum);
        float[] lightTest1 = this.dataOfAllSensor[4];
        float[] temtest1 = this.dataOfAllSensor[1];
        float remp = temtest1[0];
        float lightTest = lightTest1[0];
        String a = String.valueOf(lightTest);
        String b = String.valueOf(remp);
        Log.d(TAG,a);
        //Log.d(TAG,b);
       this.addQuestionnaireToOverviewActivity();

    }

    public void addShouldAnswerQuestionnaire(Questionnaire questionnaire){
        //这里是个过滤器，应当在这里判断
        boolean existQuestionnaire = false;//是否存在了相同的问卷

            //same Questionnaire only add once not more times.
            //if (this.shouldAnswerQuestionnaire.isEmpty()) {

                    //this.shouldAnswerQuestionnaire.add(questionnaire);

           // } else {
        //左边一直在想尝试加同一份问卷，右边不管list里有多少问卷 只是一直添加到activity当中显式
        //现在左边有限制了 但是右边还是出的去，相当于我加了一份问卷，则一直存在，右边则一直在加
                for (Questionnaire questionnaire1 : this.shouldAnswerQuestionnaire) {
                    if (questionnaire1.getQuestionnaireID().equals(questionnaire.getQuestionnaireID())) {

                        existQuestionnaire = true;
                    }
                }

                if (!existQuestionnaire) {

                        this.shouldAnswerQuestionnaire.add(questionnaire);

                }
                //else{//如果存在相同问卷 再来判断啥时候删除
                    //Calendar cal = Calendar.getInstance();
                    //if(!questionnaire.isInMiniTimeSpace(cal.getTime())){
                      //  this.shouldAnswerQuestionnaire.remove(questionnaire);
                    //}
                   // Calendar cal = Calendar.getInstance();
                    //Date currentTime = cal.getTime();
                    //if(!questionnaire.isInMiniTimeSpace(currentTime)){
                     //   ;
                    //}
                //}

            //}




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
        //这里应该是有什么加什么，不应该在这里判断
        //if(!this.shouldAnswerQuestionnaire.isEmpty()){
            for(Questionnaire questionnaire:this.shouldAnswerQuestionnaire) {

                OverviewActivity.getInstance().addQuestionnaireToTriggeredQuestionnaireList
                        (questionnaire.getQuestionnaireID());

                this.shouldAnswerQuestionnaire.remove(questionnaire);
                //Calendar cal = Calendar.getInstance();
               // Date currentTime = cal.getTime();
                //if(!questionnaire.isInMiniTimeSpace(currentTime)) {
                    //
                }

                //这里应当不需要判断是否有相同的ID 可以相同的ID 但是触发条件不同

            //}
        //}
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

   /* public boolean isInMiniTimeSpace(Questionnaire questionnaire){
        Date currentTime = TimeUtil.getCurrentTime();
        long setTime = questionnaire.getTriggeredBySensorTime()+1000*questionnaire
                .getTriggerEvent().getMinTimeSpace();
        if(currentTime.getTime()<=setTime){
            return true;
        }else{
            return false;
        }

    }*/






}
