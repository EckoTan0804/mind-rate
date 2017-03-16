package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

/**
 * This class aims to model the questionContent of the questionnaire
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/8:23:33</br>
 */

public class Question implements Parcelable {

    @SerializedName("questionContent")
    private String questionContent;

    private QuestionType questionType;
    private String questionID;
    private boolean showByDefault;
    private boolean isAnswered;

    public Question() {
    }

    public Question(String questionID) {
        this.questionID = questionID;
    }

    /**
     * Constructor
     *
     * @param questionContent content of a quesiton
     * @param questionType    question's type, one from the 5 type:
     *                        <br>
     *                        <li>SingleChoice</li>
     *                        <li>MultipleChoice</li>
     *                        <li>StepScale</li>
     *                        <li>DragScale</li>
     *                        <li>TextAnswer</li>
     *                        </br>
     * @param questionID      question's id
     * @param showByDefault   <br>
     *                        <li>false, if the question will only occur when proband has chosen a
     *                        specified option of last question</li>
     *                        <li>true, otherwise</li>
     *                        </br>
     */
    public Question(String questionContent, QuestionType questionType, String questionID,
            boolean showByDefault) {
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.questionID = questionID;
        this.showByDefault = showByDefault;
    }


    /**
     * Infalte the view for question depending on <code>questionType</code>
     *
     * @param tv_question TextView for <code>questionContent</code>
     * @param context context
     * @param layout layout for Answer area
     * @param layoutParams parameters for <code>layout</code>
     */
    public void inflateView(TextView tv_question, Context context, ViewGroup layout,
            ViewGroup.LayoutParams
                    layoutParams) {
        tv_question.setText(questionContent);
        this.questionType.inflateAnswerView(this.questionID, context, layout, layoutParams);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Question) {
            Question q = (Question)obj;
            return q.questionContent.equals(this.questionContent) && q.questionID.equals(this.questionID)
                    && q.showByDefault == this.showByDefault && q.questionType.equals(this.questionType);
        } else {
            return false;
        }

    }

    // ================ setters and getters ==================================

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
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

    public boolean isShowByDefault() {
        return showByDefault;
    }

    public void setShowByDefault(boolean showByDefault) {
        this.showByDefault = showByDefault;
    }

    public boolean isAnswered() {
        return this.questionType.isAnswered();
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    //================= Parcelable ===========================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionContent);
        dest.writeParcelable(this.questionType, flags);
        dest.writeString(this.questionID);
        dest.writeByte(this.showByDefault ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isAnswered ? (byte) 1 : (byte) 0);
    }

    protected Question(Parcel in) {
        this.questionContent = in.readString();
        this.questionType = in.readParcelable(QuestionType.class.getClassLoader());
        this.questionID = in.readString();
        this.showByDefault = in.readByte() != 0;
        this.isAnswered = in.readByte() != 0;
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
