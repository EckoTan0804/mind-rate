package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mindrate.util.FontUtil;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * This class aims to model a singleChoice question
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/10:04:15</br>
 */

public class SingleChoice extends QuestionType implements Parcelable {

    @SerializedName("options")
    private ArrayList<Option> optionList;

    public SingleChoice() {
        super("SingleChoice");
    }

    /**
     * Constructor
     *
     * @param optionList list of the offered options
     */
    public SingleChoice(ArrayList<Option> optionList) {
        super("SingleChoice");
        this.optionList = optionList;
    }

    @Override
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

        super.questionAnswer = new QuestionAnswer(questionID, "SingleChoice");

        RadioGroup radioGroup = new RadioGroup(context);

        // add radioButton into radioGroup
        for (int i = 0; i < optionList.size(); i++) {
            Option option = optionList.get(i);
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(i);
            radioButton.setText(option.getContent());
            radioGroup.addView(radioButton);
        }

        // set listener for this radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                nextQuestionID = optionList.get(checkedId).getNextQuestionID();
                questionAnswer.setAnswerContent(optionList.get(checkedId).getContent());
                setAnswered(true);
            }
        });

        layout.addView(radioGroup);

        FontUtil.changeFonts(layout, context);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SingleChoice) {
            SingleChoice singleChoice = (SingleChoice) obj;
            if (this.optionList == null && singleChoice.optionList == null) {
                return true;
            }
            if ((this.optionList == null && singleChoice.optionList != null) || (this.optionList
                    != null && singleChoice.optionList == null)) {
                return false;
            }
            if (this.optionList.size() != singleChoice.optionList.size()) {
                return false;
            } else {
                for (int i = 0; i < this.optionList.size(); i++) {
                    if (!this.optionList.get(i).equals(singleChoice.optionList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    // ==================== setters and getters ==================================================

    @Override
    public void setAnswered(boolean isAnswered) {
        super.setAnswered(isAnswered);
    }

    @Override
    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public String getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(String nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }

    public ArrayList<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }

    // =================== Parcelable ============================================================
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.optionList);
        dest.writeString(this.nextQuestionID);
    }

    protected SingleChoice(Parcel in) {
        this.optionList = in.createTypedArrayList(Option.CREATOR);
        this.nextQuestionID = in.readString();
    }

    public static final Parcelable.Creator<SingleChoice> CREATOR = new Parcelable
            .Creator<SingleChoice>() {
        @Override
        public SingleChoice createFromParcel(Parcel source) {
            return new SingleChoice(source);
        }

        @Override
        public SingleChoice[] newArray(int size) {
            return new SingleChoice[size];
        }
    };
}
