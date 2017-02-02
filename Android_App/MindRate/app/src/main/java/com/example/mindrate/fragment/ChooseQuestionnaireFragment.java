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
import com.example.mindrate.gson.DragScale;
import com.example.mindrate.gson.MultipleChoice;
import com.example.mindrate.gson.Option;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.gson.SingleChoice;
import com.example.mindrate.gson.StepScale;
import com.example.mindrate.gson.TextAnswer;

import java.util.ArrayList;
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

    private List<Questionnaire> questionnaireList;
    private Questionnaire selectedQuestionnaire;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_questionnaire, container, false);

        initTestData();

//TODO:        initQuestionnaireList();

        //        titleText = (TextView) view.findViewById(R.id.title_text);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new QuestionnaireAdapter(getContext(), R.layout.questionnaire_item,
                                           questionnaireList);
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
                selectedQuestionnaire = questionnaireList.get(position);
//                selectedQuestionnaire.sendNotification(getActivity());

                // 2. put this questionnaire instance into intent
                Intent intent = new Intent(getActivity(), AnswerQuestionnaireActivity.class);
                intent.putExtra("questionnaire", selectedQuestionnaire);

                // 3. use this intent to start AnswerQuestionnaireActivity
                getActivity().startActivity(intent);
            }
        });
    }

    private void initQuestionnaireList() {
        this.questionnaireList = ((OverviewActivity)getActivity()).getQuestionnaireList();
    }


    // test
    private void initTestData() {
        questionnaireList = new ArrayList<>();

        Questionnaire questionnaire = new Questionnaire("A", "2017.1.2 14:00", "2017.2.2 14:00");
        // q1
        ArrayList<Option> optionList = new ArrayList<>();
        optionList.add(new Option("At home", "Q3"));
        optionList.add(new Option("At work", "Q3"));
        optionList.add(new Option("on the way", "Q2"));
        Question q1 = new Question("Where are you?", new SingleChoice(optionList), "Q1");
        questionnaire.addQuestion(q1);

        // q2
        Question q2 = new Question("Where are you heading to?", new TextAnswer(), "Q2");
        questionnaire.addQuestion(q2);

        // q3
        Question q3 = new Question("How are you feeling?", new DragScale(10), "Q3");
        questionnaire.addQuestion(q3);

        // q4
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(new Option("Swimming", null));
        optionArrayList.add(new Option("Reading", null));
        optionArrayList.add(new Option("Coding", null));
        optionArrayList.add(new Option("Studying", null));
        Question q4 = new Question("What's ur hobby?", new MultipleChoice(optionArrayList), "Q4");
        questionnaire.addQuestion(q4);

        // q5
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("very bad", null));
        options.add(new Option("bad", null));
        options.add(new Option("so so", null));
        options.add(new Option("good", null));
        options.add(new Option("very good!", null));
        Question q5 = new Question("Do you like this app?", new StepScale(options), "Q5");
        questionnaire.addQuestion(q5);


        questionnaireList.add(questionnaire);
        questionnaireList.add(new Questionnaire("B", "2017.1.2", "2017.2.2"));
        questionnaireList.add(new Questionnaire("C", "2017.1.3", "2017.2.2"));
    }


}
