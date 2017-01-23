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
import android.widget.ListView;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.gson.Questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for the fragment, in which:
 *
 * <li>the proband can choose the questionnaires which are
 * already triggered but not answered yet</li>
 *
 * <p>
 * Project: MindRate
 * <br>Package: com.example.mindrate.fragment</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * </p>
 */
public class chooseQuestionnaireFragment extends Fragment {


    //====================== UI - Components ====================
    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    // ==========================================================

    private List<String> dataList = new ArrayList<>();
    private List<Questionnaire> questionnaireList;
    private Questionnaire selectedQuestionnaire;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_questionnaire, container, false);

        titleText = (TextView) view.findViewById(R.id.title_text);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), R.layout.questionnaire_item, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // TODO:
                selectedQuestionnaire = questionnaireList.get(position);
                titleText.setText(selectedQuestionnaire.getQuestionnaireID());
            }
        });
    }
}
