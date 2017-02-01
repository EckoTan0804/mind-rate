package com.example.mindrate.gson;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;

/**
 * Created by Renhan on 2017/1/9.
 */

public class QuestionType implements Parcelable {

    protected String nextQuestionID;
    protected QuestionAnswer questionAnswer;

    public  void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

    }

    public String getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(String nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public QuestionType() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nextQuestionID);
        dest.writeParcelable(this.questionAnswer, flags);
    }

    protected QuestionType(Parcel in) {
        this.nextQuestionID = in.readString();
        this.questionAnswer = in.readParcelable(QuestionAnswer.class.getClassLoader());
    }

    public static final Creator<QuestionType> CREATOR = new Creator<QuestionType>() {
        @Override
        public QuestionType createFromParcel(Parcel source) {
            return new QuestionType(source);
        }

        @Override
        public QuestionType[] newArray(int size) {
            return new QuestionType[size];
        }
    };
}
