package com.example.mindrate.gson;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Renhan on 2017/1/9.
 */

public abstract class QuestionType {

    public abstract void inflateAnswerView(Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams);


}
