package com.example.mindrate.gson;


import java.util.Collection;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

public class Questionnaire {


    public static final String SERVER_ADDRESS = "Server Address"; //TODO: give the real address!

    private Collection<Question> questions;
    private boolean isValid;
    private String beginTime;
    private String endTime;
    private String submitTime;
    private int duration;
    private String questionnaireID;
    private boolean shouldTrigger;
    private boolean[] sensorValues;
    private boolean triggeredBySensor;
    private TriggerEvent sensorManager;

    public Questionnaire(String questionnaireID,String beginTime, String endTime) {
        this.questionnaireID = questionnaireID;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    //   private Collection<> answers;
//   public SensorManager SensorManager;


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
            // collect all questions' answers of this questionnaire
            // upload answers
        }
    }

    /**
     * Send notification when questionnaire is triggered
     */
    public void notifyToAnswer() {
        // send notification
    }


    // ================ setters and getters ==================================

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
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

    public boolean isShouldTrigger() {
        return shouldTrigger;
    }

    public void setShouldTrigger(boolean shouldTrigger) {
        this.shouldTrigger = shouldTrigger;
    }

    public boolean[] getSensorValues() {
        return sensorValues;
    }

    public void setSensorValues(boolean[] sensorValues) {
        this.sensorValues = sensorValues;
    }

    public boolean isTriggeredBySensor() {
        return triggeredBySensor;
    }

    public void setTriggeredBySensor(boolean triggeredBySensor) {
        this.triggeredBySensor = triggeredBySensor;
    }

    public String getQuestionnaireID() {
        return questionnaireID;
    }

    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public TriggerEvent getSensorManager() {
        return sensorManager;
    }

    public void setSensorManager(TriggerEvent sensorManager) {
        this.sensorManager = sensorManager;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
