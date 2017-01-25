package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;

public class AnswerQuestionActivity extends AppCompatActivity {

    private Proband proband;

    // ==================== View components ==================================
    private DrawerLayout mDrawerLayout;
    private Button btn_nav;

    // =======================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        initFromIntent();
        initView();

//        tv_questionText.setText(Utility.createJSON(this.proband));
    }

    private void initFromIntent() {
        Intent intent = getIntent();
        String questionnaire = intent.getStringExtra("questionnaire_JSON");
        this.proband = (Proband) intent.getParcelableExtra("proband");
    }

    private void initView() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // =================== Button nav ===================================
        btn_nav = (Button) findViewById(R.id.nav);
        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // ==================================================================
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.switch_fragment, fragment);
        transition.commit();
    }
}
