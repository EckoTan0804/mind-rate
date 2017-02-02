package com.example.mindrate.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mindrate.R;
import com.example.mindrate.gson.Proband;

public class MainActivity extends BaseActivity {

    private static boolean isLogIn = false;
    private Proband proband;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent;
        if (!isLogIn) {
            intent = new Intent(this, LogInActivity.class);
        } else {
            intent = new Intent(this, OverviewActivity.class);
        }

        startActivity(intent);

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
}
