package com.example.mindrate.util;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Project: MindRate
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/9:16:16</br>
 * </p>
 */

public class PreferenceUtil {

    private static SharedPreferences mSharedPreferences = null;
    private static SharedPreferences.Editor mEditor = null;

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences
                    (context);
        }
    }

    public static void removeKey(String key) {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    public static void removeAll() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    public static void commitString(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getString(String key, String faillValue){
        return mSharedPreferences.getString(key, faillValue);
    }
}
