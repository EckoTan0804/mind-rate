package com.example.mindrate.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mindrate.R;
import com.example.mindrate.activity.AnswerQuestionnaireActivity;
import com.example.mindrate.activity.OverviewActivity;
import com.example.mindrate.adapter.QuestionnaireAdapter;
import com.example.mindrate.gson.Questionnaire;

import java.util.List;

/**
 * This class is for the fragment, in which:
 * <p>
 * <li>the proband can choose the questionnaires which are
 * already triggered but not answered yet</li>
 * <p>
 * <p>
 * Project: MindRate
 * <br>Package: com.example.mindrate.fragment</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * </p>
 */
public class ChooseQuestionnaireFragment extends Fragment {


    //====================== UI - Components ====================
    private ProgressDialog progressDialog;
    //    private TextView titleText;
    private ListView listView;
    private QuestionnaireAdapter adapter;
    // ==========================================================

    private List<Questionnaire> triggeredQuestionnaireList;
    private Questionnaire selectedQuestionnaire;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_questionnaire, container, false);

        initTestData();

        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new QuestionnaireAdapter(getContext(), R.layout.questionnaire_item,
                                           triggeredQuestionnaireList);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // 1. get the selected questionnaire instance
                selectedQuestionnaire = triggeredQuestionnaireList.get(position);
                OverviewActivity overviewActivity = (OverviewActivity) getActivity();
                overviewActivity.setSelectedQuestionnaire(selectedQuestionnaire);
                //                selectedQuestionnaire.sendNotification(getActivity());

                // 2. put this questionnaire instance into intent
                Intent intent = new Intent(overviewActivity, AnswerQuestionnaireActivity.class);
                intent.putExtra("questionnaire", selectedQuestionnaire);

                // 3. use this intent to start AnswerQuestionnaireActivity
                getActivity().startActivityForResult(intent, 1);
            }
        });
    }

    public QuestionnaireAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(QuestionnaireAdapter adapter) {
        this.adapter = adapter;
    }

    private void initQuestionnaireList() {
        //        this.triggeredQuestionnaireList = ((OverviewActivity)getActivity())
        // .getAllQuestionnaireList();
        OverviewActivity overviewActivity = (OverviewActivity) getActivity();
        this.triggeredQuestionnaireList = overviewActivity.getTriggeredQuestionnaireList();
    }


    // test
    private void initTestData() {
        initQuestionnaireList();
    }


}
