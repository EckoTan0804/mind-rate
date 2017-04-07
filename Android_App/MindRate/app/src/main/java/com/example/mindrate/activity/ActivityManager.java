package com.example.mindrate.activity;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to manage app's activities
 *
 * Project: MindRate
 * Package: com.example.mindrate.activity
 * Author: Ecko Tan
 * E-mail: ecko0804@gmail.com
 * Created at 2017/1/10:17:30
 */

public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<Activity>();

    /**
     * Add an new activity to <code>activityList</code>
     *
     * @param activity activity to be added
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * Remove activity from <code>activityList</code>
     *
     * @param activity activity to be removed
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * End all activities
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
