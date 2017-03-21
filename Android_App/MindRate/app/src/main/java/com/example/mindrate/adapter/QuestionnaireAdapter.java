package com.example.mindrate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.util.TimeUtil;

import java.util.List;


/**
 * Custom adapter for listView
 *
 * Project: MindRate
 * Package: com.example.mindrate.adapter
 * Author: Ecko Tan
 * E-mail: eckotan@icloud.com
 * Created at 2017/1/23:11:14
 */

public class QuestionnaireAdapter extends ArrayAdapter<Questionnaire> {

    private int resourceID;

    /**
     * Constructor
     *
     * @param context context
     * @param resource layout resource
     * @param objects list of data that should be shown
     */
    public QuestionnaireAdapter(Context context, int resource, List<Questionnaire> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Questionnaire questionnaire = getItem(position);
        View view = null;
        ViewHolder viewHolder;

        // improve the efficiency of ListView
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_questionnaireID = (TextView) view.findViewById(R.id.list_item_questionnaire_id);
            viewHolder.tv_beginTime = (TextView) view.findViewById(R.id.list_item_questionnaire_begin_time);
            viewHolder.tv_endTime = (TextView) view.findViewById(R.id.list_item_questionnaire_end_time);
            view.setTag(viewHolder); // store ViewHolder in View
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (TextUtils.isEmpty(questionnaire.getQuestionnaireID())) {
            questionnaire.setQuestionnaireID("probandInfoQuestionnaire");
        }
        viewHolder.tv_questionnaireID.setText(questionnaire.getQuestionnaireID());

        // TODO: set beginTime
        if (questionnaire.getTriggerTime() != null) {
            viewHolder.tv_beginTime.setText(TimeUtil.parseDate(questionnaire.getTriggerTime()));
        }

        // TODO: set endTime
        if (questionnaire.getEndTime() != null) {
            viewHolder.tv_endTime.setText(TimeUtil.parseDate(questionnaire.getEndTime()));
        }

        return view;


    }

    /**
     * ViewHolder for list item, in order to improve the inflation's efficiency
     */
    class ViewHolder {

        TextView tv_questionnaireID;
        TextView tv_beginTime;
        TextView tv_endTime;
    }
}
