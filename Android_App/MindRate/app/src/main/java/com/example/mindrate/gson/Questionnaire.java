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
import com.example.mindrate.activity.OverviewActivity;
import com.example.mindrate.util.TimeUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PRIVATE;

/**
 * This class aims to model a questionnaire
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/8:23:32</br>
 */

public class Questionnaire implements Parcelable, Observer, Cloneable {

    private static final String TAG = "Questionnaire";
    private static int currentQuestionIndex = 0;

    public static final String SERVER_ADDRESS = "Server Address"; //TODO: give the real address!

    @Expose
    private boolean isValid;

    @Expose(serialize = false, deserialize = false)
    private String studyID;

    @SerializedName("questionnaireID")
    private String questionnaireID;
    //private float[][]datenOfTriggeredSensor = new float[12][3];
    //=========Daten of triggered Sensor ===========
    // TODO: have no Parcelable!!
    private float temperature; //1
    private float light;//4
    private float relativeHumidity;//10
    private float airPressure;//8
    private float proximity;//9
    private float[] linearAcceleration = new float[3];//5
    //=======================================


    /**
     * the newest trigger time
     */
    private Date triggerTime;
    private Date endTime;
    private Date submitTime;

    @SerializedName("duration")
    private Duration duration;

    /**
     * the list of questions
     */
    @SerializedName("questions")
    private ArrayList<Question> questionList;

    private TriggerEvent triggerEvent;






    //==============triggered Data of Sensor===========
    private float ambientTemperatureValue = -10000;
    private float lightValue = -10000;
    private float pressureValue = -10000;
    private float proximityValue = -10000;
    private float relativeHumidityValue = -10000;
    //===========================================================
    public float getAmbientTemperatureValue() {
        return ambientTemperatureValue;
    }

    public float getLightValue() {
        return lightValue;
    }

    public float getPressureValue() {
        return pressureValue;
    }

    public float getProximityValue() {
        return proximityValue;
    }

    public float getRelativeHumidityValue() {
        return relativeHumidityValue;
    }

    //=========================================================

    /**
     * Constructor
     */
    public Questionnaire() {
        this.questionList = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param questionnaireID questionnaire's id
     */
    public Questionnaire(String questionnaireID) {
        this.questionnaireID = questionnaireID;
        this.questionList = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param studyID         study's id
     * @param questionnaireID study's id
     */
    public Questionnaire(String studyID, String questionnaireID) {
        this.studyID = studyID;
        this.questionnaireID = questionnaireID;
        this.questionList = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param questionnaireID questionnaire's id
     * @param duration        how long will a questionnaire last
     */
    public Questionnaire(String questionnaireID,
            Duration duration) {
        this.questionnaireID = questionnaireID;
        this.duration = duration;
        this.questionList = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param studyID         study's id
     * @param questionnaireID study's id
     * @param duration        study's id
     */
    public Questionnaire(String studyID, String questionnaireID,
            Duration duration) {
        this.studyID = studyID;
        this.questionnaireID = questionnaireID;
        this.duration = duration;
        this.questionList = new ArrayList<>();
    }

    /**
     * Send notification to remind the proband to answer the questionnaire which is just triggered
     *
     * @param context context
     */
    public void sendNotification(Context context) {
        Intent intent = new Intent(context,
                                   OverviewActivity.class);
        intent.putExtra("notityToAnswer", "chooseQuestionnaireFragment");
        intent.putExtra("questionnaire", this);
        PendingIntent pi = PendingIntent.getActivity(context,
                                                     0,
                                                     intent,
                                                     FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.questionnair_is_triggered))
                .setContentText(context.getString(R.string.click_to_answer))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_mr)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                                           R.mipmap.ic_mr))
                .setVisibility(VISIBILITY_PRIVATE)
                .setContentIntent(pi).setAutoCancel(true)
                .build();
        manager.notify(0, notification);

    }

    /**
     * Add <code>question</code> to <code>questionList</code>
     *
     * @param question question to be added
     */
    public void addQuestion(Question question) {
        this.questionList.add(question);
    }

    /**
     * get a question according to the given <code>questionID</code>
     *
     * @param questionID id of the target question
     * @return <li>the question whose id is <code>questionID</code> if it exists in
     * <code>questionList</code></li>
     * <li>null, otherwise</li>
     */
    public Question getQuestion(String questionID) {
        Question targetQuestion = null;
        for (Question q : questionList) {
            if (q.getQuestionID()
                    .equals(questionID)) {
                targetQuestion = q;
            }
        }
        return targetQuestion;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//<<<<<<< HEAD
//    // TODO: need to improve this method according to showByDefault
//    public String defaultNextQuestionID(Question currentQuestion) {
//        String nextQuestionID = null;
//        int currentQuestionIndex = this.questionList.lastIndexOf(currentQuestion);
//        if (!isLastQuestion(currentQuestion)) {
//            nextQuestionID = this.questionList.get(currentQuestionIndex + 1)
//                                              .getQuestionID();
//        }
//        return nextQuestionID;
//    }
//=======
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Questionnaire) {
            Questionnaire questionnaire = (Questionnaire) obj;
            if (this.questionList.size() != questionnaire.questionList.size()) {
                // different size of questionList --> questionnaire not equals
                return false;
            } else {
                for (int i = 0; i < this.questionList.size(); i++) {
                    if (!this.questionList.get(i).equals(questionnaire.questionList.get(i))) {
                        // if an element not equals --> questionnaire not equals
                        return false;
                    }
                }
                /* if questionList equals,
                 * then compare other attributes.
                 */
                return this.questionnaireID.equals(questionnaire.questionnaireID) &&
//                        this.studyID.equals(questionnaire.studyID) &&
                        this.duration.equals(questionnaire.duration) &&
                        TimeUtil.parseDate(triggerTime).equals(TimeUtil.parseDate(triggerTime));
                //                        && this.triggerEvent.equals(questionnaire.triggerEvent)e

            }

        } else {
            return false;
        }
    }

    /**
     * Create a copy for questionnaire ifself
     *
     * @return a copy of this questionnaire
     */
    public Questionnaire cloneItself() {
        Questionnaire q = null;
        try {
            q = (Questionnaire) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            Log.d(TAG, "cloneItself: triggered failed due to unsuccessful clone");
        } finally {

        }
        return q;
    }

    /**
     * Get the id of the next question which is <code>showByDefault</code>
     *
     * @param currentQuestion the current displaying question
     * @return <li>the id of next <code>showByDefault</code> question if it exists </li>
     * <li>null, otherwise</li>
     */
    public String defaultNextQuestionID(Question currentQuestion) {
        //        String nextQuestionID = null;
        int currentQuestionIndex = this.questionList.lastIndexOf(currentQuestion);
        for (int i = currentQuestionIndex + 1; i < this.questionList.size(); i++) {
            Question q = questionList.get(i);
            if (q.isShowByDefault()) {
                return q.getQuestionID();
            }
        }
        return null;
    }

    //    public boolean isLastQuestion(Question question) {
    //        if (this.questionList.lastIndexOf(question) == (this.questionList.size() - 1)) {
    //            return true;
    //        }
    //        return false;
    //    }
    //

    /**
     * The actions when a questionnaire is triggered
     *
     * @param context context
     */
    public void trigger(Context context) {

        // 1. mark down triggerTime
        this.triggerTime = TimeUtil.getCurrentTime();

        // 2. calculate EndTime
        try {
            this.endTime = TimeUtil.calculateTime(triggerTime,
                                                  duration);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {

        }

        // 3. send notification
        this.sendNotification(context);
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

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }


    public void update(Observable o, Object arg) {
        TriggerEventManager triggerEventManager = (TriggerEventManager) o;
        float[][] dataOfAllSensor = triggerEventManager.getDataOfAllSensor();



        if (this.compareDataOfAllSensor(dataOfAllSensor)) {
            this.setSensorDataWhenTriggered(dataOfAllSensor);
            //TimeUtil?
            //Calendar cal = Calendar.getInstance();
            //this.triggeredBySensorTime = cal.getTimeInMillis();
            if (this.triggerTime == null) {
                triggerEventManager.addShouldAnswerQuestionnaire(this);

            } else {
                Date lastTriggerTime = this.triggerTime;
                Date currentTime = TimeUtil.getCurrentTime();
                long nextTriggerTime = lastTriggerTime.getTime() + this.triggerEvent
                        .getMinTimeSpace() * 1000;
                if (currentTime.getTime() >= nextTriggerTime) {
                    triggerEventManager.addShouldAnswerQuestionnaire(this);
                    Log.i(TAG,String.valueOf(this.temperature));
                }
            }

        }

        //================testData==============================================
        float[] lightTest1 = dataOfAllSensor[4];
        float[] temtest1 = dataOfAllSensor[1];
        float temp = temtest1[0];
        float lightTest = lightTest1[0];
        float[] pressure = dataOfAllSensor[8];
        //        String a = String.valueOf(lightTest);
        String b = String.valueOf(temp);
        //        Log.d(TAG,a);
        Log.d(TAG, "pressure: " + pressure[0]);
        Log.d(TAG, "temp: " + temp);
        Log.d(TAG, "Proximity: " + dataOfAllSensor[9][0]);


        //========================================================================
        // send to Proband a Notification.
    }

    private void addDatenofSensorToQuestionnaire(float[][] dataOfAllSensor){
        boolean[] sensorList=this.triggerEvent.getSensorList();
        for(int i = 0;i<sensorList.length;i++){
            if(sensorList[i]){
                switch(i){
                    case 1:
                        this.temperature = dataOfAllSensor[1][0];
                        break;
                    case 4:
                        this.light = dataOfAllSensor[4][0];
                        break;
                    case 5:
                        this.linearAcceleration = dataOfAllSensor[5];
                        break;
                    case 8:
                        this.airPressure = dataOfAllSensor[8][0];
                        break;
                    case 9:
                        this.proximity = dataOfAllSensor[9][0];
                        break;
                    default:
                        this.relativeHumidity = dataOfAllSensor[10][0];
                        break;
                }
            }
        }

    }

    //=================compare the time to miniTimeSpace===========================
   /* public boolean isInMiniTimeSpace(Date currentTime){
        Date now = currentTime;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentTime);
        cal1.add(Calendar.SECOND,this.triggerEvent.getMinTimeSpace());
        long setTime = cal1.getTime().getTime();

        if(now.getTime()<=setTime){
            return true;
        }else{
            return false;
        }
    }*/

    //=====================================================================
    //========================compare the date of all sensor to the trigger condition===========
    //===========return true if this questionnaire's triggered condition are reached========
    private boolean compareDataOfAllSensor(float[][] inputDataOfAllSensor) {
        //isSensorTrigger shows
        boolean isSensorTrigger = false;
        // isSensorTrigger = false||(true&&sensor1&&sensor2…) wenn all sensor are triggered,then
        // sensor are triggered.if it has one sensor are not triggered,will return false.
        // (compound condition)
        boolean helpVariate = true;
        boolean[] sensorList = new boolean[12];
        float[][] dataOfAllSensor = inputDataOfAllSensor;
        for (int i = 0; i < this.triggerEvent.getSensorList().length; i++) {
            if (this.triggerEvent.getSensorList()[i]) {
                switch (i) {
                    case 0:
                        sensorList[0] = this.compareDataOfAccelerometerSensor(dataOfAllSensor[0][1],
                                                                              dataOfAllSensor[0][2],
                                                                              dataOfAllSensor[0][3]);
                        break;
                    case 1:
                        sensorList[1] = this.compareDataOfTemperatureSensor(dataOfAllSensor[1][0]);
                        break;
                    case 2:
                        sensorList[2] = this.compareDataOfGravitySensor(dataOfAllSensor[2][0],
                                                                        dataOfAllSensor[2][1],
                                                                        dataOfAllSensor[2][2]);
                        break;
                    case 3:
                        sensorList[3] = this.compareDataOfGyroscopeSensor(dataOfAllSensor[3][0],
                                                                          dataOfAllSensor[3][1],
                                                                          dataOfAllSensor[3][2]);
                        break;
                    case 4:
                        sensorList[4] = this.compareDataOfLightSensor(dataOfAllSensor[4][0]);
                        break;
                    case 5:
                        sensorList[5] = this.compareDataOfLinearAccelerationSensor
                                (dataOfAllSensor[5][0],
                                 dataOfAllSensor[5][1], dataOfAllSensor[5][2]);
                        break;
                    case 6:
                        sensorList[6] = this.compareDataOfMagneticFieldSensor(dataOfAllSensor[6][0],
                                                                              dataOfAllSensor[6][1],
                                                                              dataOfAllSensor[6][2]);
                        break;
                    case 7:
                        sensorList[7] = this.compareDataOfOrientationSensor(dataOfAllSensor[7][0],
                                                                            dataOfAllSensor[7][1],
                                                                            dataOfAllSensor[7][2]);
                        break;
                    case 8:
                        sensorList[8] = this.compareDataOfPressureSensor(dataOfAllSensor[8][0]);
                        break;
                    case 9:
                        sensorList[9] = this.compareDataOfProximitySensor(dataOfAllSensor[9][0]);
                        break;
                    case 10:
                        sensorList[10] = this.compareDataOfRelativeHumiditySensor
                                (dataOfAllSensor[10][0]);
                        break;
                    default:
                        sensorList[11] = this.compareDataOfRotationVectorSensor
                                (dataOfAllSensor[11][0],
                                 dataOfAllSensor[11][1], dataOfAllSensor[11][2]);
                        break;
                }
            }
        }

        for (int i = 0; i < this.triggerEvent.getSensorList().length; i++) {
            if (this.triggerEvent.getSensorList()[i]) {
                helpVariate = helpVariate && sensorList[i];
            }
        }

        return isSensorTrigger || helpVariate;

    }
    //=================================================================================================
    private void setSensorDataWhenTriggered(float[][] dataOfAllSensor){
        if(this.triggerEvent.isAmbientTemperature()){
            this.ambientTemperatureValue = dataOfAllSensor[1][0];
        }
        if(this.triggerEvent.isLight()){
            this.lightValue = dataOfAllSensor[4][0];
        }
        if(this.triggerEvent.isPressure()){
            this.pressureValue = dataOfAllSensor[8][0];
        }
        if(this.triggerEvent.isProximity()){
            this.proximityValue = dataOfAllSensor[9][0];
        }
        if(this.triggerEvent.isRelativeHumidity()){
            this.relativeHumidityValue = dataOfAllSensor[10][0];
        }



    }

    //=============================================================================
    private boolean compareDatetoAnswer() {
        return true;

    }

    //================methode for every sensor,compare the data to its condition=================
    private boolean compareDataOfAccelerometerSensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getAccelerometerMinXValue() && xValue <= this.triggerEvent
                .getAccelerometerMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getAccelerometerMinXValue() && yValue <= this.triggerEvent
                .getAccelerometerMaxXValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getAccelerometerMinXValue() && zValue <= this.triggerEvent
                .getAccelerometerMaxXValue()) {
            z = true;
        }


        return x && y && z;
    }

    private boolean compareDataOfTemperatureSensor(float xValue) {
        boolean x = false;
        if (xValue >= this.triggerEvent
                .getAmbientTemperatureMinValue() && xValue <= this.triggerEvent
                .getAmbientTemperatureMaxValue()) {
            x = true;
        }
        return x;
    }

    private boolean compareDataOfGravitySensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getGravityMinXValue() && xValue <= this.triggerEvent
                .getGravityMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getGravityMinYValue() && yValue <= this.triggerEvent
                .getGravityMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getGravityMinZValue() && zValue <= this.triggerEvent
                .getGravityMaxZValue()) {
            z = true;
        }


        return x && y && z;
    }

    private boolean compareDataOfGyroscopeSensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getGyroscopeMinXValue() && xValue <= this.triggerEvent
                .getGyroscopeMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getGyroscopeMinYValue() && yValue <= this.triggerEvent
                .getGyroscopeMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getGyroscopeMinZValue() && zValue <= this.triggerEvent
                .getGyroscopeMaxZValue()) {
            z = true;
        }

        return x && y && z;
    }

    private boolean compareDataOfLightSensor(float xValue) {
        boolean x = false;
        if (xValue >= this.triggerEvent.getLightMinValue() && xValue <= this.triggerEvent
                .getLightMaxValue()) {
            x = true;
        }
        return x;
    }

    private boolean compareDataOfLinearAccelerationSensor(float xValue, float yValue,
            float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent
                .getLinearAccelerationMinXValue() && xValue <= this.triggerEvent
                .getLinearAccelerationMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent
                .getLinearAccelerationMinYValue() && yValue <= this.triggerEvent
                .getLinearAccelerationMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent
                .getLinearAccelerationMinZValue() && zValue <= this.triggerEvent
                .getLinearAccelerationMaxZValue()) {
            z = true;
        }

        return x && y && z;
    }

    private boolean compareDataOfMagneticFieldSensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getMagneticFieldMinXValue() && xValue <= this.triggerEvent
                .getMagneticFieldMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getMagneticFieldMinYValue() && yValue <= this.triggerEvent
                .getMagneticFieldMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getMagneticFieldMinZValue() && zValue <= this.triggerEvent
                .getMagneticFieldMaxZValue()) {
            z = true;
        }

        return x && y && z;
    }

    private boolean compareDataOfOrientationSensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getOrientationMinXValue() && xValue <= this.triggerEvent
                .getOrientationMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getOrientationMinYValue() && yValue <= this.triggerEvent
                .getOrientationMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getOrientationMinZValue() && zValue <= this.triggerEvent
                .getOrientationMaxZValue()) {
            z = true;
        }

        return x && y && z;
    }

    private boolean compareDataOfPressureSensor(float xValue) {
        boolean x = false;
        if (xValue >= this.triggerEvent.getPressureMinValue() && xValue <= this.triggerEvent
                .getPressureMaxValue()) {
            x = true;
        }
        return x;
    }

    private boolean compareDataOfProximitySensor(float xValue) {
        boolean x = false;
        if (xValue >= this.triggerEvent.getProximityMinValue() && xValue <= this.triggerEvent
                .getProximityMaxValue()) {
            x = true;
        }
        return x;
    }

    private boolean compareDataOfRelativeHumiditySensor(float xValue) {
        boolean x = false;
        if (xValue >= this.triggerEvent.getRelativeHumidityMinValue() && xValue <= this.triggerEvent
                .getRelativeHumidityMaxValue()) {
            x = true;
        }
        return x;
    }

    private boolean compareDataOfRotationVectorSensor(float xValue, float yValue, float zValue) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        if (xValue >= this.triggerEvent.getRotationVectorMinXValue() && xValue <= this.triggerEvent
                .getRotationVectorMaxXValue()) {
            x = true;
        }

        if (yValue >= this.triggerEvent.getRotationVectorMinYValue() && yValue <= this.triggerEvent
                .getRotationVectorMaxYValue()) {
            y = true;
        }

        if (zValue >= this.triggerEvent.getRotationVectorMinZValue() && zValue <= this.triggerEvent
                .getRotationVectorMaxZValue()) {
            z = true;
        }

        return x && y && z;
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
        dest.writeString(this.questionnaireID);;
        dest.writeLong(this.triggerTime != null ? this.triggerTime.getTime() : -1);
        dest.writeLong(this.endTime != null ? this.endTime.getTime() : -1);
        dest.writeLong(this.submitTime != null ? this.submitTime.getTime() : -1);
        dest.writeParcelable(this.duration, flags);
        dest.writeTypedList(this.questionList);
        dest.writeParcelable(this.triggerEvent, flags);
        dest.writeFloat(this.ambientTemperatureValue);
        dest.writeFloat(this.lightValue);
        dest.writeFloat(this.pressureValue);
        dest.writeFloat(this.proximityValue);
        dest.writeFloat(this.relativeHumidityValue);
    }

    protected Questionnaire(Parcel in) {
        this.isValid = in.readByte() != 0;
        this.studyID = in.readString();
        this.questionnaireID = in.readString();
        long tmpTriggerTime = in.readLong();
        this.triggerTime = tmpTriggerTime == -1 ? null : new Date(tmpTriggerTime);
        long tmpEndTime = in.readLong();
        this.endTime = tmpEndTime == -1 ? null : new Date(tmpEndTime);
        long tmpSubmitTime = in.readLong();
        this.submitTime = tmpSubmitTime == -1 ? null : new Date(tmpSubmitTime);
        this.duration = in.readParcelable(Duration.class.getClassLoader());
        this.questionList = in.createTypedArrayList(Question.CREATOR);
        this.triggerEvent = in.readParcelable(TriggerEvent.class.getClassLoader());
        this.ambientTemperatureValue = in.readFloat();
        this.lightValue = in.readFloat();
        this.pressureValue = in.readFloat();
        this.proximityValue = in.readFloat();
        this.relativeHumidityValue = in.readFloat();
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
