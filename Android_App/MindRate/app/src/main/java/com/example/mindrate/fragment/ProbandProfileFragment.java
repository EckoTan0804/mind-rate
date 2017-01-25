package com.example.mindrate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mindrate.R;
import com.example.mindrate.activity.AnswerQuestionActivity;
import com.example.mindrate.gson.Proband;


public class ProbandProfileFragment extends Fragment {

    private Proband proband;
    private TextView tv_profileStudyID;
    private TextView tv_profileProbandID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proband_profile, container, false);

        AnswerQuestionActivity answerQuestionActivity = (AnswerQuestionActivity) getActivity();
        this.proband = answerQuestionActivity.getProband();
        tv_profileStudyID = (TextView) view.findViewById(R.id.profile_study_id);
        tv_profileStudyID.setText(proband.getStudyID());
        tv_profileProbandID = (TextView) view.findViewById(R.id.profile_proband_id);
        tv_profileProbandID.setText(proband.getProbandID());
        return view;
    }

    public Proband getProband() {
        return proband;
    }

    public void setProband(Proband proband) {
        this.proband = proband;
    }
}
