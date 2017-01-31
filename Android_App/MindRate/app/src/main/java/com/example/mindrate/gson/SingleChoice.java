package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:15
 */

public class SingleChoice extends QuestionType implements Parcelable {

    private ArrayList<Option> optionlist;
    private String nextQuestionID;

    public SingleChoice(ArrayList<Option> optionlist) {
        this.optionlist = optionlist;
    }

    @Override
    public void inflateAnswerView(Context context, ViewGroup layout, ViewGroup.LayoutParams
            layoutParams) {

        RadioGroup radioGroup = new RadioGroup(context);

        // add radioButton into radioGroup
        for (int i = 0; i < optionlist.size(); i++) {
            Option option = optionlist.get(i);
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(i);
            radioButton.setText(option.getContent());
            radioGroup.addView(radioButton);
        }

        // set listener for this radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                nextQuestionID = optionlist.get(checkedId).getNextQuestionID();
            }
        });

        layout.addView(radioGroup);
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
        dest.writeTypedList(this.optionlist);
        dest.writeString(this.nextQuestionID);
    }

    protected SingleChoice(Parcel in) {
        this.optionlist = in.createTypedArrayList(Option.CREATOR);
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
