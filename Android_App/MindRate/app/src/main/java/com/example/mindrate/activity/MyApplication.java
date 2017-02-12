package com.example.mindrate.activity;

import android.app.Application;

import com.example.mindrate.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.activity</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/12:19:44</br>
 * </p>
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                              .setDefaultFontPath("fonts/RobotoMono-Light.ttf")
                                              .setFontAttrId(R.attr.fontPath)
                                              .build()
        );

    }
}
