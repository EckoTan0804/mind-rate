package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:22:41</br>
 * </p>
 */

public class Option implements Parcelable {

    private String content;
    private String nextQuestion;

    public Option(String content, String nextQuestion) {
        this.content = content;
        this.nextQuestion = nextQuestion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(String nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.nextQuestion);
    }

    protected Option(Parcel in) {
        this.content = in.readString();
        this.nextQuestion = in.readString();
    }

    public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}
