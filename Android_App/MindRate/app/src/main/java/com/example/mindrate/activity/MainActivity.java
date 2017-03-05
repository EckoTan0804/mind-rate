package com.example.mindrate.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;
import com.example.mindrate.gson.Questionnaire;
import com.example.mindrate.util.JsonUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements Animation.AnimationListener {

    private static final String[] LANGUAGES = {"English", "Deutsch"};
    private static final String[] LANGUAGE_ARRAY = {"en", "de"};

    private static boolean isLogIn = false;

    private Proband proband;
    private String selectedLanguage = "en";
    private CircleImageView appIcon;
    private Button btn_chooseLanguage;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // test json parsing

        String testJson = JsonUtil.createJSON(JsonUtil.initJsonTestData());
        List<Questionnaire> list = JsonUtil.fromJsonToQuestionnaireList(testJson);

        // ===================================================

        super.onCreate(savedInstanceState);

        // set English as default language
        switchLanguage("en");

        setContentView(R.layout.activity_main);

        appIcon = (CircleImageView) findViewById(R.id.app_icon_image);
        btn_chooseLanguage = (Button) findViewById(R.id.choose_language);

        btn_chooseLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogStyle);

                builder.setTitle(R.string.choose_language);
                builder.setSingleChoiceItems(LANGUAGES, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedLanguage = LANGUAGE_ARRAY[i];
                    }
                });
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switchLanguage(selectedLanguage);
                        redirectTo();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

//        clockWise();
        myAnim();
    }


    public static boolean isLogIn() {
        return isLogIn;
    }

    public static void setIsLogIn(boolean isLogIn) {
        MainActivity.isLogIn = isLogIn;
    }

    public Proband getProband() {
        return proband;
    }

    public void setProband(Proband proband) {
        this.proband = proband;
    }

    private void redirectTo() {
        // TODO: remove comment symbol for next line
//        isLogIn = PreferenceUtil.getBoolean("isLogIn", false);
        Intent intent;
        if (!isLogIn) {
            intent = new Intent(this, LogInActivity.class);
        } else {
            intent = new Intent(this, OverviewActivity.class);
        }
        startActivity(intent);
    }


    // ========================== Animation =====================================

    private void myAnim() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim
                .my_anim);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }


    private void clockWise() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim
                .clockwise);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void zoom() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R
                .anim.zoom);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void fade() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void blink() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void move() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void slide() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        appIcon.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
//        appIcon.setVisibility(View.INVISIBLE);
        btn_chooseLanguage.setVisibility(View.VISIBLE);
    }
}
