package com.example.mindrate.gson;


import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
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

public class TextAnswer extends QuestionType implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public TextAnswer() {
    }

    protected TextAnswer(Parcel in) {
    }

    public static final Parcelable.Creator<TextAnswer> CREATOR = new Parcelable
            .Creator<TextAnswer>() {
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
