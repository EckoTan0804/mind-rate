package com.example.mindrate.gson;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class aims to model a answer of a question
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/1/30:13:09</br>
 * </p>
 */

public class QuestionAnswer implements Parcelable {

    private String questionID;
    @SerializedName("answer")
    private String answerContent;

    private String questionType;

    /**
     * Constructor
     *
     * @param questionID   id of the question that the <code>questionAnswer</code> belongs to
     * @param questionType type of the question that the <code>questionAnswer</code> belongs to
     */
    public QuestionAnswer(String questionID, String questionType) {
        this.questionID = questionID;
        this.questionType = questionType;
    }

    /**
     * Constructor
     *
     * @param questionID id of the question that the <code>questionAnswer</code> belongs to
     */
    public QuestionAnswer(String questionID) {
        this.questionID = questionID;
    }

    //    /**
    //     * Constructor
    //     *
    //     * @param questionID id of the question that the <code>questionAnswer</code> belongs to
    //     * @param answerContent the input answer for this question
    //     */
    //    public QuestionAnswer(String questionID, String answerContent) {
    //        this.questionID = questionID;
    //        this.answerContent = answerContent;
    //    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof QuestionAnswer) {
            QuestionAnswer questionAnswer = (QuestionAnswer) obj;
            return this.questionID.equals(questionAnswer.questionID) && this.answerContent.equals
                    (questionAnswer.answerContent);
        } else {
            return false;
        }
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

    // ===================== Parcelable =====================================================

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
