package com.example.mindrate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;

import java.util.List;


/**
 * Project: MindRate
 * Package: com.example.mindrate.adapter
 * Author: Ecko Tan
 * E-mail: eckotan@icloud.com
 * Created at 2017/1/23:11:14
 */

public class QuestionnaireAdapter extends ArrayAdapter<Questionnaire> {

    private int resourceID;

    public QuestionnaireAdapter(Context context, int resource, List<Questionnaire> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Questionnaire questionnaire = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);

        TextView tv_questionnaireName = (TextView) view.findViewById(R.id.questionnaire_name);
        tv_questionnaireName.setText(questionnaire.getQuestionaireID());

        // TODO: set beginTime
        TextView tv_beginTime = (TextView) view.findViewById(R.id.questionnaire_begin_time);
        tv_beginTime.setText(questionnaire.getBeginTime());

        // TODO: set endTime
        TextView tv_EndTime = (TextView) view.findViewById(R.id.questionnaire_end_time);
        tv_beginTime.setText(questionnaire.getEndTime());
        return view;
    }
}
