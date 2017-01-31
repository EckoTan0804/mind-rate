package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:32
 */

public class Questionnaire implements Parcelable,Observer {


    public static final String SERVER_ADDRESS = "Server Address"; //TODO: give the real address!


    private boolean isValid;

    private String studyID;
    private String questionnaireID;

    private String beginTime;
    private String endTime;
    private String submitTime;
    private int duration;

    private List<Question> questions;

    private TriggerEvent triggerEvent;


    public Questionnaire(String questionnaireID, String beginTime, String endTime) {
        this.questionnaireID = questionnaireID;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
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
        dest.writeList(this.questions);
        dest.writeParcelable(this.triggerEvent, flags);
    }

    protected Questionnaire(Parcel in) {
        this.isValid = in.readByte() != 0;
        this.studyID = in.readString();
        this.questionnaireID = in.readString();
        this.beginTime = in.readString();
        this.endTime = in.readString();
        this.submitTime = in.readString();
        this.duration = in.readInt();
        this.questions = new ArrayList<Question>();
        in.readList(this.questions, Question.class.getClassLoader());
        this.triggerEvent = in.readParcelable(TriggerEvent.class.getClassLoader());
    }

    public static final Parcelable.Creator<Questionnaire> CREATOR = new Parcelable.Creator<Questionnaire>() {
        @Override
        public Questionnaire createFromParcel(Parcel source) {
            return new Questionnaire(source);
        }

        @Override
        public Questionnaire[] newArray(int size) {
            return new Questionnaire[size];
        }
    };
    public void update(Observable o, Object arg){
        TriggerEventManager tEM=(TriggerEventManager) o;
        // send to Proband a Notification.
    }
}
