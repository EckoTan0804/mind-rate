package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class aims to model the options of singleChoice question and stepScale question
 * <p>
 * Project: MindRate
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:22:41</br>
 * </p>
 */

public class Option implements Parcelable {

    @SerializedName("optionContent")
    private String content;
    @SerializedName("nextQuestionID")
    private String nextQuestionID;


    /**
     * Constructor
     *
     * @param content        content of the option
     * @param nextQuestionID the id of next question that should occurs if the proband chose this
     *                       option
     */
    public Option(String content, String nextQuestionID) {
        this.content = content;
        this.nextQuestionID = nextQuestionID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Option) {
            Option option = (Option) obj;
            if (this.nextQuestionID != null && option.nextQuestionID != null) {
                return this.content.equals(option.content) && this.nextQuestionID.equals(option.nextQuestionID);
            } else if (this.nextQuestionID == null && option.nextQuestionID == null){
                return this.content.equals(option.content);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        dest.writeString(this.content);
        dest.writeString(this.nextQuestionID);
    }

    protected Option(Parcel in) {
        this.content = in.readString();
        this.nextQuestionID = in.readString();
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
