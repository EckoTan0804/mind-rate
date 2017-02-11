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
        // send to Proband a Notification.
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
