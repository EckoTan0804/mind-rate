package com.example.mindrate.gson;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.mindrate.R;
import com.example.mindrate.activity.AnswerQuestionnaireActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

public class Questionnaire implements Parcelable, Observer {
    private static final String TAG = "Questionnaire";


    public static final String SERVER_ADDRESS = "Server Address"; //TODO: give the real address!


    private boolean isValid;

    private String studyID;
    private String questionnaireID;

    private String beginTime;
    private String endTime;
    private String submitTime;
    private int duration;

    private ArrayList<Question> questionList;

    private TriggerEvent triggerEvent;

    private boolean isAnswered;


    public Questionnaire(String questionnaireID, String beginTime, String endTime) {
        this.questionnaireID = questionnaireID;
        this.beginTime = beginTime;
        this.endTime = endTime;
        questionList = new ArrayList<>();
    }

    /**
     * The answers of the questionnaire will be temporarily saved in smartphone.
     */
    public void saveAnswerLocally() {
        // sharePreference
    }


    /**
     * Upload the answers to the server if the questionnaire is still valid
     *
     * @param serverAddr the address of server
     */
    public void uploadAnswers(String serverAddr) {
        if (isValid) {
            // collect all questionList' answers of this questionnaire
            // upload answers
        }
    }

    /**
     * Send notification when questionnaire is triggered
     */
    public void sendNotification(Context context) {
        Intent intent = new Intent(context, AnswerQuestionnaireActivity.class);
        intent.putExtra("questionnaire", this);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context).setContentTitle("You " +
                "hava a new questionnaire").setContentText("Questionnaire " + this
                .questionnaireID + " is waiting for your answer").setWhen(System
                .currentTimeMillis()).setSmallIcon(R.mipmap.ic_thumb_up).setLargeIcon
                (BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_thumb_up))
                .setContentIntent(pi).build();
        manager.notify(1, notification);

    }

    public void addQuestion(Question question) {
        this.questionList.add(question);
    }

    public Question getQuestion(String questionID) {
        Question targetQuestion = null;
        for (Question q : questionList) {
            if (q.getQuestionID().equals(questionID)) {
                targetQuestion = q;
            }
        }
        return targetQuestion;
    }

    public boolean isLastQuestion(String questionID) {
        if (questionID.equals(this.questionList.get(this.questionList.size() - 1).getQuestionID()
        )) {
            return true;
        }
        return false;
    }

    public String defaultNextQuestionID(Question currentQuestion) {
        String nextQuestionID = null;
        int currentQuestionIndex = this.questionList.lastIndexOf(currentQuestion);
        if (!isLastQuestion(currentQuestion)) {
            nextQuestionID = this.questionList.get(currentQuestionIndex + 1).getQuestionID();
        }
        return nextQuestionID;
    }

    public boolean isLastQuestion(Question question) {
        if (this.questionList.lastIndexOf(question) == (this.questionList.size() - 1)) {
            return true;
        }
        return false;
    }

    // ================ setters and getters ==================================

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public TriggerEvent getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(TriggerEvent triggerEvent) {
        this.triggerEvent = triggerEvent;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    // ===================== Parcelable ==========================================================

    public void update(Observable o, Object arg) {
        TriggerEventManager triggerEventManager= (TriggerEventManager) o;
        float[][] dataOfAllSensor = triggerEventManager.getDataOfAllSensor();
        //===================写成一个方法==========================================
        if(this.compareDataOfAllSensor(dataOfAllSensor)) {
            triggerEventManager.addShouldAnswerQuestionnaire(this);
            //如何把questionnaire加到OverviewActivity；

        }

        //================testData==============================================
        float[] lightTest1 = dataOfAllSensor[4];
        float[] temtest1 = dataOfAllSensor[1];
        float remp = temtest1[0];
        float lightTest = lightTest1[0];
        String a = String.valueOf(lightTest);
        String b = String.valueOf(remp);
        Log.d(TAG,a);
        Log.d(TAG,b);
        //========================================================================
        // send to Proband a Notification.
    }
//========================compare the date of all sensor to the trigger condition===========
    //===========return true if this questionnaire's triggered condition are reached========
    public boolean compareDataOfAllSensor(float[][] inputDataOfAllSensor){
        //isSensorTrigger shows
        boolean isSensorTrigger = false;
        // isSensorTrigger = false||(true&&sensor1&&sensor2…) wenn all sensor are triggered,then
        // sensor are triggered.if it has one sensor are not triggered,will return false.(compound condition)
        boolean helpVariate = true;
        boolean[] sensorList= new boolean[12];//用到的sensor是否达到触发条件
        float[][] dataOfAllSensor = inputDataOfAllSensor;
        for(int i =0;i<this.triggerEvent.getSensorList().length;i++){
            if(this.triggerEvent.getSensorList()[i]){
                switch(i){
                    case 0:
                        sensorList[0]=this.compareDataOfAccelerometerSensor(dataOfAllSensor[0][1],
                                dataOfAllSensor[0][2],dataOfAllSensor[0][3]);
                        break;
                    case 1:
                        sensorList[1]=this.compareDataOfTemperatureSensor(dataOfAllSensor[1][0]);
                        break;
                    case 2:
                        sensorList[2]=this.compareDataOfGravitySensor(dataOfAllSensor[2][0],
                                dataOfAllSensor[2][1],dataOfAllSensor[2][2]);
                        break;
                    case 3:
                        sensorList[3]=this.compareDataOfGyroscopeSensor(dataOfAllSensor[3][0],
                                dataOfAllSensor[3][1],dataOfAllSensor[3][2]);
                        break;
                    case 4:
                        sensorList[4]=this.compareDataOfLightSensor(dataOfAllSensor[4][0]);
                        break;
                    case 5:
                        sensorList[5]=this.compareDataOfLinearAccelerationSensor
                                (dataOfAllSensor[5][0],
                                dataOfAllSensor[5][1],dataOfAllSensor[5][2]);
                        break;
                    case 6:
                        sensorList[6]=this.compareDataOfMagneticFieldSensor(dataOfAllSensor[6][0],
                                dataOfAllSensor[6][1],dataOfAllSensor[6][2]);
                        break;
                    case 7:
                        sensorList[7]=this.compareDataOfOrientationSensor(dataOfAllSensor[7][0],
                                dataOfAllSensor[7][1],dataOfAllSensor[7][2]);
                        break;
                    case 8:
                        sensorList[8]=this.compareDataOfPressureSensor(dataOfAllSensor[8][0]);
                        break;
                    case 9:
                        sensorList[9]=this.compareDataOfProximitySensor(dataOfAllSensor[9][0]);
                        break;
                    case 10:
                        sensorList[10]=this.compareDataOfRelativeHumiditySensor
                                (dataOfAllSensor[10][0]);
                        break;
                    default:
                        sensorList[11]=this.compareDataOfRotationVectorSensor
                                (dataOfAllSensor[11][0],
                                dataOfAllSensor[11][1],dataOfAllSensor[11][2]);
                        break;
                }
            }
        }

        for(int i=0;i<this.triggerEvent.getSensorList().length;i++){
            if(this.triggerEvent.getSensorList()[i]){
                helpVariate=helpVariate&&sensorList[i];
            }
        }
        return isSensorTrigger||helpVariate;

    }
    //=================================================================================================

    private boolean compareDatetoAnswer(){
        return true;

    }

    //================methode for every sensor,compare the data to its condition=================
    private boolean compareDataOfAccelerometerSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getAccelerometerMinXValue()&&xValue<=this.triggerEvent
                .getAccelerometerMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getAccelerometerMinXValue()&&yValue<=this.triggerEvent
                .getAccelerometerMaxXValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getAccelerometerMinXValue()&&zValue<=this.triggerEvent
                .getAccelerometerMaxXValue()){
            z= true;
        }


        return x&&y&&z;
    }

    private boolean compareDataOfTemperatureSensor(float xValue){
        boolean x=false;
        if(xValue>=this.triggerEvent.getAmbientTemperatureMinValue()&&xValue<=this.triggerEvent.getAmbientTemperatureMaxValue()){
            x= true;
        }
        return x;
    }

    private boolean compareDataOfGravitySensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getGravityMinXValue()&&xValue<=this.triggerEvent.getGravityMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getGravityMinYValue()&&yValue<=this.triggerEvent.getGravityMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getGravityMinZValue()&&zValue<=this.triggerEvent.getGravityMaxZValue()){
            z= true;
        }


        return x&&y&&z;
    }

    private boolean compareDataOfGyroscopeSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getGyroscopeMinXValue()&&xValue<=this.triggerEvent
                .getGyroscopeMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getGyroscopeMinYValue()&&yValue<=this.triggerEvent
                .getGyroscopeMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getGyroscopeMinZValue()&&zValue<=this.triggerEvent
                .getGyroscopeMaxZValue()){
            z= true;
        }

        return x&&y&&z;
    }
    private boolean compareDataOfLightSensor(float xValue){
        boolean x=false;
        if(xValue>=this.triggerEvent.getLightMinValue()&&xValue<=this.triggerEvent.getLightMaxValue()){
            x= true;
        }
        return x;
    }

    private boolean compareDataOfLinearAccelerationSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getLinearAccelerationMinXValue()&&xValue<=this.triggerEvent
                .getLinearAccelerationMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getLinearAccelerationMinYValue()&&yValue<=this.triggerEvent
                .getLinearAccelerationMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getLinearAccelerationMinZValue()&&zValue<=this.triggerEvent
                .getLinearAccelerationMaxZValue()){
            z= true;
        }

        return x&&y&&z;
    }

    private boolean compareDataOfMagneticFieldSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getMagneticFieldMinXValue()&&xValue<=this.triggerEvent
                .getMagneticFieldMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getMagneticFieldMinYValue()&&yValue<=this.triggerEvent
                .getMagneticFieldMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getMagneticFieldMinZValue()&&zValue<=this.triggerEvent
                .getMagneticFieldMaxZValue()){
            z= true;
        }

        return x&&y&&z;
    }

    private boolean compareDataOfOrientationSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getOrientationMinXValue()&&xValue<=this.triggerEvent
                .getOrientationMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getOrientationMinYValue()&&yValue<=this.triggerEvent
                .getOrientationMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getOrientationMinZValue()&&zValue<=this.triggerEvent
                .getOrientationMaxZValue()){
            z= true;
        }

        return x&&y&&z;
    }

    private boolean compareDataOfPressureSensor(float xValue){
        boolean x=false;
        if(xValue>=this.triggerEvent.getPressureMinValue()&&xValue<=this.triggerEvent.getPressureMaxValue()){
            x= true;
        }
        return x;
    }

    private boolean compareDataOfProximitySensor(float xValue){
        boolean x=false;
        if(xValue>=this.triggerEvent.getProximityMinValue()&&xValue<=this.triggerEvent.getProximityMaxValue()){
            x= true;
        }
        return x;
    }

    private boolean compareDataOfRelativeHumiditySensor(float xValue){
        boolean x=false;
        if(xValue>=this.triggerEvent.getRelativeHumidityMinValue()&&xValue<=this.triggerEvent.getRelativeHumidityMaxValue()){
            x= true;
        }
        return x;
    }

    private boolean compareDataOfRotationVectorSensor(float xValue,float yValue,float zValue){
        boolean x=false;
        boolean y=false;
        boolean z=false;
        if(xValue>=this.triggerEvent.getRotationVectorMinXValue()&&xValue<=this.triggerEvent
                .getRotationVectorMaxXValue()){
            x= true;
        }

        if(yValue>=this.triggerEvent.getRotationVectorMinYValue()&&yValue<=this.triggerEvent
                .getRotationVectorMaxYValue()){
            y= true;
        }

        if(zValue>=this.triggerEvent.getRotationVectorMinZValue()&&zValue<=this.triggerEvent
                .getRotationVectorMaxZValue()){
            z= true;
        }

        return x&&y&&z;
    }
    //========================================================================================================



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isValid ? (byte) 1 : (byte) 0);
        dest.writeString(this.studyID);
        dest.writeString(this.questionnaireID);
        dest.writeString(this.beginTime);
        dest.writeString(this.endTime);
        dest.writeString(this.submitTime);
        dest.writeInt(this.duration);
        dest.writeTypedList(this.questionList);
        dest.writeParcelable(this.triggerEvent, flags);
        dest.writeByte(this.isAnswered ? (byte) 1 : (byte) 0);
    }

    protected Questionnaire(Parcel in) {
        this.isValid = in.readByte() != 0;
        this.studyID = in.readString();
        this.questionnaireID = in.readString();
        this.beginTime = in.readString();
        this.endTime = in.readString();
        this.submitTime = in.readString();
        this.duration = in.readInt();
        this.questionList = in.createTypedArrayList(Question.CREATOR);
        this.triggerEvent = in.readParcelable(TriggerEvent.class.getClassLoader());
        this.isAnswered = in.readByte() != 0;
    }

    public static final Creator<Questionnaire> CREATOR = new Creator<Questionnaire>() {
        @Override
        public Questionnaire createFromParcel(Parcel source) {
            return new Questionnaire(source);
        }

        @Override
        public Questionnaire[] newArray(int size) {
            return new Questionnaire[size];
        }
    };
}
