package com.example.mindrate.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.example.mindrate.R;
import com.example.mindrate.activity.OverviewActivity;
import com.example.mindrate.util.PreferenceUtil;


public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences
        .OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    private static final String KEY_PREF_Language = "languagePref";
    private static final String PREF_ENGLISH = "en";
    private static final String ENGLISH = "English";
    private static final String PREF_DEUTSCH = "de";
    public static final String DEUTSCH = "Deutsch";

    SharedPreferences sharedPreferences;
    private ListPreference listP_language;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        addPreferencesFromResource(R.xml.settings_preference);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
        initPref();
    }

    private void initPref() {

        OverviewActivity overviewActivity = (OverviewActivity)getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(overviewActivity);

        // init language pref using the already chosen language
        listP_language = (ListPreference) findPreference(KEY_PREF_Language);
        String chosenLanguage = PreferenceUtil.getString("language", "");
        int index = 0;
        switch(chosenLanguage){
            case "en":
                break;
            case "de":
                index = 1;
                break;
            default:

                break;
        }
        listP_language.setValueIndex(index);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {

            case KEY_PREF_Language:

                // get language which is just chosen and stored in sharedPreference
                String language = sharedPreferences.getString(key, "");

                // change language
                OverviewActivity overviwActivity = (OverviewActivity) getActivity();
                overviwActivity.switchLanguageImmediately(language);
                break;

            default:

                break;
        }

    }

    private void setSummary(String prefLanguage) {
        switch(prefLanguage){
            case PREF_ENGLISH:
                listP_language.setSummary(ENGLISH);
                break;
            case PREF_DEUTSCH:
                listP_language.setSummary(DEUTSCH);
                break;
            default:

                break;
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener
                (this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener
                (this);
        super.onPause();
    }
}
