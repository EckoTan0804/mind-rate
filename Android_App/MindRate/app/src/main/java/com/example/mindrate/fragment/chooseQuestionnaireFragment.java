package com.example.mindrate.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Question;
import com.example.mindrate.gson.Questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for the fragment, in which the proband can choose the questionnaires which are
 * already triggered but not answered yet
 *
 * Project: MindRate
 * Package: com.example.mindrate.fragment
 * Author: Ecko Tan
 * E-mail: eckotan@icloud.com
 */
public class chooseQuestionnaireFragment extends Fragment {

    public static final int LEVLE_QUESTIONNAIRE = 0;
    public static final int LEVEL_QUESTION = 1;

    //====================== UI - Components ====================
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    // ==========================================================

    private List<String> dataList = new ArrayList<>();
    private List<Questionnaire> questionnaireList;
    private List<Question> questionList;
    private Questionnaire selectedQuestionnaire;
    private Question selectedQuestion;
    private int currentLevel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_questionnaire, container, false);

        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView= (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (currentLevel == LEVLE_QUESTIONNAIRE) {
                    selectedQuestionnaire =  questionnaireList.get(position);
                } else if (currentLevel == LEVEL_QUESTION) {
                    selectedQuestion = questionList.get(position);
                }
            }
        });
    }
}
