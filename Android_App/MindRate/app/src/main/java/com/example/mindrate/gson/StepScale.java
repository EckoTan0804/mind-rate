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
 * This class aims to model StepScale question.
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/10:04:19</br>
 */

public class StepScale extends QuestionType implements Parcelable {

    @SerializedName("options")
    private ArrayList<Option> optionList;

    public StepScale() {
        super("StepScale");
    }

    /**
     * Constructor
     *
     * @param optionList the offered options (StepScale is kind of <code>SingleChoice</code>)
     */
    public StepScale(ArrayList<Option> optionList) {
        super("StepScale");
        this.optionList = optionList;
    }

    @Override
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

        super.questionAnswer = new QuestionAnswer(questionID, "StepScale");

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
        if (obj != null && obj instanceof StepScale) {
            StepScale stepScale = (StepScale) obj;
            if (this.optionList.size() != stepScale.optionList.size()) {
                return false;
            } else {
                for (int i = 0; i < this.optionList.size(); i++) {
                    if (!this.optionList.get(i).equals(stepScale.optionList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    // ================== setters and getters ==================================================
    @Override
    public void setAnswered(boolean isAnswered) {
        super.setAnswered(true);
    }

    public String getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(String nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }

    // =========================================================================================

    // ================== Parcelable ===========================================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.optionList);
        dest.writeString(this.nextQuestionID);
    }

    protected StepScale(Parcel in) {
        this.optionList = in.createTypedArrayList(Option.CREATOR);
        this.nextQuestionID = in.readString();
    }

    public static final Parcelable.Creator<StepScale> CREATOR = new Parcelable.Creator<StepScale>
            () {
        @Override
        public StepScale createFromParcel(Parcel source) {
            return new StepScale(source);
        }

        @Override
        public StepScale[] newArray(int size) {
            return new StepScale[size];
        }
    };
}
