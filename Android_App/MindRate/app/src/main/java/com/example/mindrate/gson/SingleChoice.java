package com.example.mindrate.gson;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:15
 */

public class SingleChoice extends QuestionType {

    private List<Option> optionlist;
    private String nextQuestionID;

    public SingleChoice(List<Option> optionlist) {
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
    }

    public String getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(String nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }
}
