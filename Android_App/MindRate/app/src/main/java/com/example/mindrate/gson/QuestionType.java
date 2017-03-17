package com.example.mindrate.gson;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.google.gson.annotations.Expose;

/**
 * This class aims to model type of the question.
 * The type of a question can be on from below:
 * <ul>
 * <li>SingleChoice</li>
 * <li>MultipleChoice</li>
 * <li>StepScale</li>
 * <li>DragScale</li>
 * <li>TextAnswer</li>
 * </ul>
 * These 5 types will be modelled as the subclasses of this class.
 * <p>
 * Created by Renhan on 2017/1/9.
 */

public class QuestionType implements Parcelable {

    protected String nextQuestionID;
    protected QuestionAnswer questionAnswer;

    @Expose(deserialize = false)
    protected String typeName;

    private boolean isAnswered;

    /**
     * Constructor
     *
     * @param typeName name of the type
     */
    public QuestionType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * This method will be override by different subclasses.
     *
     * @param questionID   question's id
     * @param context      context
     * @param layout       layout for answer area
     * @param layoutParams layout's parameter
     */
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof QuestionType) {
            QuestionType questionType = (QuestionType)obj;
            return this.getTypeName().equals(questionType.typeName);
        } else {
            return false;
        }
    }

    // =================== setters and getters =====================================================
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    // =============================================================================================

    // =================== Parcelable ==============================================================

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
        dest.writeString(this.typeName);
        dest.writeByte(this.isAnswered ? (byte) 1 : (byte) 0);
    }

    protected QuestionType(Parcel in) {
        this.nextQuestionID = in.readString();
        this.questionAnswer = in.readParcelable(QuestionAnswer.class.getClassLoader());
        this.typeName = in.readString();
        this.isAnswered = in.readByte() != 0;
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
