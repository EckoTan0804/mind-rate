package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/8:23:33
 */

public class Question implements Parcelable {

    @SerializedName("questionContent")
    private String question;

    @SerializedName("questionType")
    private QuestionType questionType;

    @SerializedName("questionID")
    private String questionID;
    private boolean isAnswered;
    private boolean isValid;
    private boolean isBeginToAnswer;

    @SerializedName("showByDefault")
    private boolean showByDefault;

    public Question(String question, QuestionType questionType, String questionID) {
        this.question = question;
        this.questionType = questionType;
        this.questionID = questionID;
        this.isAnswered = false;
        isBeginToAnswer = false;
    }

    public Question(String question, QuestionType questionType, String questionID,
            boolean showByDefault) {
        this.question = question;
        this.questionType = questionType;
        this.questionID = questionID;
        this.showByDefault = showByDefault;
    }

    public void inflateView(TextView tv_question, Context context, ViewGroup layout, ViewGroup.LayoutParams
            layoutParams) {
        tv_question.setText(question);
        this.questionType.inflateAnswerView(this.questionID, context, layout, layoutParams);
    }

    // ================ setters and getters ==================================

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isBeginToAnswer() {
        return isBeginToAnswer;
    }

    public void setBeginToAnswer(boolean beginToAnswer) {
        isBeginToAnswer = beginToAnswer;
    }

    public boolean isShowByDefault() {
        return showByDefault;
    }

    public void setShowByDefault(boolean showByDefault) {
        this.showByDefault = showByDefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeParcelable(this.questionType, flags);
        dest.writeString(this.questionID);
        dest.writeByte(this.isAnswered ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isValid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isBeginToAnswer ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showByDefault ? (byte) 1 : (byte) 0);
    }

    protected Question(Parcel in) {
        this.question = in.readString();
        this.questionType = in.readParcelable(QuestionType.class.getClassLoader());
        this.questionID = in.readString();
        this.isAnswered = in.readByte() != 0;
        this.isValid = in.readByte() != 0;
        this.isBeginToAnswer = in.readByte() != 0;
        this.showByDefault = in.readByte() != 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
