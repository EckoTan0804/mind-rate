package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private static boolean isLogIn = false;
    private Proband proband;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);

        final CircleImageView appIcon = (CircleImageView) view.findViewById(R.id.app_icon_image);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(5000);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });




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
}
