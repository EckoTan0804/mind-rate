package com.example.mindrate.util;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class aims to execute the preference operations:
 *      save data locally, and
 *      read from loacl memory
 * for different data types.
 *
 * <p>
 * <br>Project: MindRate</br>
 * <br>Package: com.example.mindrate.util</br>
 * <br>Author: Ecko Tan</br>
 * <br>E-mail: eckotan@icloud.com</br>
 * <br>Created at 2017/2/9:16:16</br>
 * </p>
 */

public class PreferenceUtil {

    private static SharedPreferences mSharedPreferences = null;
    private static SharedPreferences.Editor mEditor = null;

    /**
     * Initialization for Preference operations.
     *
     * @param context context
     */
    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences
                    (context);
        }
    }

    /**
     * Remove a key from Peference
     *
     * @param key name of the key to be removed
     */
    public static void removeKey(String key) {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    /**
     * Clean preference
     */
    public static void removeAll() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * Save String in preference
     *
     * @param key key
     * @param value String to be stored
     */
    public static void commitString(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * Get String from preference according to <code>key</code>
     *
     * @param key key
     * @param failValue return value when search with <code>key</code> fails
     * @return corresponding String according to <code>key</code>
     */
    public static String getString(String key, String failValue){
        return mSharedPreferences.getString(key, failValue);
    }

    /**
     * Save Int in preference
     *
     * @param key key
     * @param value Int to be stored
     */
    public static void commitInt(String key, int value){
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }


    /**
     * Get Int from preference according to <code>key</code>
     *
     * @param key key
     * @param failValue return value when search with <code>key</code> fails
     * @return corresponding Int according to <code>key</code>
     */
    public static int getInt(String key, int failValue){
        return mSharedPreferences.getInt(key, failValue);
    }

    /**
     * Save Long in preference
     *
     * @param key key
     * @param value Long to be stored
     */
    public static void commitLong(String key, long value){
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }


    /**
     * Get Long from preference according to <code>key</code>
     *
     * @param key key
     * @param failValue return value when search with <code>key</code> fails
     * @return corresponding Long according to <code>key</code>
     */
    public static long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }

    /**
     * Save Boolean in preference
     *
     * @param key key
     * @param value Boolean to be stored
     */
    public static void commitBoolean(String key, boolean value){
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }


    /**
     * Get Boolean from preference according to <code>key</code>
     *
     * @param key key
     * @param failValue return value when search with <code>key</code> fails
     * @return corresponding Boolean according to <code>key</code>
     */
    public static Boolean getBoolean(String key, boolean failValue){
        return mSharedPreferences.getBoolean(key, failValue);
    }
}
