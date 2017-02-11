package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements Animation.AnimationListener {

    private static boolean isLogIn = false;
    private Proband proband;
    private CircleImageView appIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appIcon = (CircleImageView) findViewById(R.id.app_icon_image);

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
        redirectTo();
    }
}
