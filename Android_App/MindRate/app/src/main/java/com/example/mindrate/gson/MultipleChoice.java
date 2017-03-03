package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mindrate.util.FontUtil;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:17
 */

public class MultipleChoice extends QuestionType implements CompoundButton
        .OnCheckedChangeListener, Parcelable {

    @SerializedName("options")
    private ArrayList<Option> optionList;
    private ArrayList<String> answerList;

    public MultipleChoice(ArrayList<Option> optionList) {
        this.optionList = optionList;
        this.answerList = new ArrayList<>();
    }

    @Override
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

        super.questionAnswer = new QuestionAnswer(questionID);

        for (int i = 0; i <optionList.size(); i++) {
            Option option = optionList.get(i);
            CheckBox checkBox = new CheckBox(context);
            checkBox.setId(i);
            checkBox.setText(option.getContent());
            checkBox.setOnCheckedChangeListener(this);
            layout.addView(checkBox);
        }

        FontUtil.changeFonts(layout, context);
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }


    @Override
    public QuestionAnswer getQuestionAnswer() {
        questionAnswer.setAnswerContent(this.answerList.toString());
        return questionAnswer;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            this.answerList.add(compoundButton.getText().toString());
        } else {
            this.answerList.remove(compoundButton.getText().toString());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.optionList);
        dest.writeStringList(this.answerList);
    }

    protected MultipleChoice(Parcel in) {
        this.optionList = in.createTypedArrayList(Option.CREATOR);
        this.answerList = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MultipleChoice> CREATOR = new Parcelable
            .Creator<MultipleChoice>() {
        @Override
        public MultipleChoice createFromParcel(Parcel source) {
            return new MultipleChoice(source);
        }

        @Override
        public MultipleChoice[] newArray(int size) {
            return new MultipleChoice[size];
        }
    };
}
