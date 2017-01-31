package com.example.mindrate.gson;


import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:21
 */

public class TextAnswer extends QuestionType {

    @Override
    public void inflateAnswerView(Context context, ViewGroup layout, ViewGroup.LayoutParams
            layoutParams) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        EditText mEditText = new EditText(context);
        mEditText.setTextSize(15);
        mEditText.setTextColor(Color.BLACK);
        mEditText.setHint("Please input your answer");

        layout.addView(mEditText, params);
    }
}
