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

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements Animation.AnimationListener {

    private static boolean isLogIn = false;
    private Proband proband;
    private CircleImageView appIcon;
    private Button btn_chooseLanguage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appIcon = (CircleImageView) findViewById(R.id.app_icon_image);
        btn_chooseLanguage = (Button) findViewById(R.id.choose_language);

        btn_chooseLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.choose_language);
                final String[] languages = {"English", "Deutsch"};
                final String[] languageArray = {"en", "de"};
                builder.setItems(languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String language = languageArray[i];
                        switchLanguage(language);
                        redirectTo();
                    }
                });
                builder.show();
            }
        });

        clockWise();
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
                .move);
        appIcon.startAnimation(animation);
        Animation secondAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim
                .clockwise);
        appIcon.startAnimation(secondAnimation);
        secondAnimation.setAnimationListener(this);
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
        appIcon.setVisibility(View.GONE);
        btn_chooseLanguage.setVisibility(View.VISIBLE);
//        redirectTo();
    }
}
