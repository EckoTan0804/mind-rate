package com.example.mindrate.gson;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Project: MindRate
 * Package: com.example.mindrate.gson
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:04:20
 */

public class DragScale extends QuestionType {

    private int maxValue;

    public DragScale(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void inflateAnswerView(Context context, ViewGroup layout, ViewGroup.LayoutParams
            layoutParams) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // =============== SeekBar ================================================
        SeekBar seekBar = new SeekBar(context);
        seekBar.setMax(maxValue);
        layout.addView(seekBar, params);

        // =============== TextView ===============================================
        final TextView tv_seekBarProgress = new TextView(context);
        layout.addView(tv_seekBarProgress, params);

        // =============== set listener for seekBar ===============================
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tv_seekBarProgress.setText(progress + " / " + maxValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
