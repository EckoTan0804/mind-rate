package com.example.mindrate.gson;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:17
 */

public class MultipleChoice extends QuestionType implements CompoundButton.OnCheckedChangeListener {

    private List<Option> optionList;
    private List<String> answerList;

    public MultipleChoice(List<Option> optionList) {
        this.optionList = optionList;
        this.answerList = new ArrayList<>();
    }

    @Override
    public void inflateAnswerView(Context context, ViewGroup layout, ViewGroup.LayoutParams
            layoutParams) {
        for (int i = 0; i <optionList.size(); i++) {
            Option option = optionList.get(i);
            CheckBox checkBox = new CheckBox(context);
            checkBox.setId(i);
            checkBox.setText(option.getContent());
            checkBox.setOnCheckedChangeListener(this);
            layout.addView(checkBox);
        }
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            this.answerList.add(compoundButton.getText().toString());
        } else {
            this.answerList.remove(compoundButton.getText().toString());
        }
    }
}
