package com.example.mindrate.gson;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mindrate.util.FontUtil;

/**
 *
 * This class aims to model DragScale question
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.gson</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: ecko0804@gmail.com</br>
 * <br>Created at 2017/1/10:04:20</br>
 */

public class DragScale extends QuestionType implements Parcelable {

    /**
     * maximal value for the seekBar
     */
    private int maxValue;

    public DragScale() {
        super("DragScale");
    }

    /**
     * Constructor
     *
     * @param maxValue maximal value for the seekBar
     */
    public DragScale(int maxValue) {
        super("DragScale");
        this.maxValue = maxValue;
    }

    @Override
    public void inflateAnswerView(String questionID, Context context, ViewGroup layout, ViewGroup
            .LayoutParams
            layoutParams) {

        super.questionAnswer = new QuestionAnswer(questionID);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // =============== SeekBar ================================================
        SeekBar seekBar = new SeekBar(context);
        seekBar.setMax(maxValue);
        layout.addView(seekBar, params);

        // =============== TextView ===============================================
        final TextView tv_seekBarProgress = new TextView(context);
        tv_seekBarProgress.setGravity(Gravity.CENTER_HORIZONTAL);
        tv_seekBarProgress.setText("  / " + maxValue);
        params.setMargins(0, 20, 0, 0);
        layout.addView(tv_seekBarProgress, params);

        // =============== set listener for seekBar ===============================
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tv_seekBarProgress.setText(progress + " / " + maxValue);
                questionAnswer.setAnswerContent(Integer.toString(progress));
                setAnswered(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        FontUtil.changeFonts(layout, context);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DragScale) {
            DragScale dragScale = (DragScale) obj;
            return this.maxValue == dragScale.maxValue;
        } else {
            return false;
        }
    }

    // ================== setters and getters ======================================
    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void setAnswered(boolean isAnswered) {
        super.setAnswered(true);
    }

    // =============================================================================

    // ================= Parcelable ================================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.maxValue);
    }

    protected DragScale(Parcel in) {
        this.maxValue = in.readInt();
    }

    public static final Parcelable.Creator<DragScale> CREATOR = new Parcelable.Creator<DragScale>
            () {
        @Override
        public DragScale createFromParcel(Parcel source) {
            return new DragScale(source);
        }

        @Override
        public DragScale[] newArray(int size) {
            return new DragScale[size];
        }
    };
}
