package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:13:09</br>
 * </p>
 */

public class QuestionAnswer implements Parcelable {

    private String questionID;
    private String answerContent;

    public QuestionAnswer(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionID);
        dest.writeString(this.answerContent);
    }

    protected QuestionAnswer(Parcel in) {
        this.questionID = in.readString();
        this.answerContent = in.readString();
    }

    public static final Parcelable.Creator<QuestionAnswer> CREATOR = new Parcelable
            .Creator<QuestionAnswer>() {
        @Override
        public QuestionAnswer createFromParcel(Parcel source) {
            return new QuestionAnswer(source);
        }

        @Override
        public QuestionAnswer[] newArray(int size) {
            return new QuestionAnswer[size];
        }
    };
}
