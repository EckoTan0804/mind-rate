package com.example.mindrate.gson;


import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mindrate.R;
import com.example.mindrate.util.FontUtil;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:21
 */

public class TextAnswer extends QuestionType implements Parcelable {

    private String inputAnswer;

    @Override
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

        super.questionAnswer = new QuestionAnswer(questionID);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText mEditText = new EditText(context);
        mEditText.setBackgroundResource(R.drawable.rounded_edittext);
        mEditText.setTextSize(15);
//        mEditText.setTextColor(ContextCompat.getColor(context,R.color.textColorPrimary));
        mEditText.setTextColor(Color.BLACK);
        mEditText.setPadding(60, 60, 60, 60);
        mEditText.setGravity(Gravity.CENTER_VERTICAL);
//        mEditText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_write, 0, 0, 0);
//        mEditText.setCompoundDrawablePadding(15);
//        mEditText.setHint("Please input your answer");


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputAnswer = editable.toString();
            }
        });

        layout.addView(mEditText, params);

        FontUtil.changeFonts(layout, context);
    }

    @Override
    public QuestionAnswer getQuestionAnswer() {
        questionAnswer.setAnswerContent(inputAnswer);
        return questionAnswer;
    }

    public TextAnswer() {
        super("TextAnswer");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.inputAnswer);
    }

    protected TextAnswer(Parcel in) {
        super(in);
        this.inputAnswer = in.readString();
    }

    public static final Creator<TextAnswer> CREATOR = new Creator<TextAnswer>() {
        @Override
        public TextAnswer createFromParcel(Parcel source) {
            return new TextAnswer(source);
        }

        @Override
        public TextAnswer[] newArray(int size) {
            return new TextAnswer[size];
        }
    };
}
