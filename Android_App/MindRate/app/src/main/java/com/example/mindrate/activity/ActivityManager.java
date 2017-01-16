package com.example.mindrate.activity;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: MindRate
 * Package: com.example.mindrate.activity
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:17:30
 */

public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
