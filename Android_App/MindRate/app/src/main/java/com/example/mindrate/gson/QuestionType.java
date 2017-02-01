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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nextQuestionID);
    }

    public QuestionType() {
    }

    protected QuestionType(Parcel in) {
        this.nextQuestionID = in.readString();
    }

    public static final Parcelable.Creator<QuestionType> CREATOR = new Parcelable
            .Creator<QuestionType>() {
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
