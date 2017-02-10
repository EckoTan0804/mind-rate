package com.example.mindrate.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.example.mindrate.R;
import com.example.mindrate.activity.OverviewActivity;


public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences
        .OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    private static final String KEY_PREF_Language = "languagePref";
    private static final String PREF_ENGLISH = "en";
    private static final String ENGLISH = "English";
    private static final String PREF_DEUTSCH = "de";
    public static final String DEUTSCH = "Deutsch";

    SharedPreferences sharedPreferences;
    private ListPreference language_pref;

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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        language_pref = (ListPreference) findPreference(KEY_PREF_Language);
        setSummary(sharedPreferences.getString(KEY_PREF_Language, "en"));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case KEY_PREF_Language:

                // get language which is already set and stored in sharedPreference
                String language = sharedPreferences.getString(key, "en");

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
                language_pref.setSummary(ENGLISH);
                break;
            case PREF_DEUTSCH:
                language_pref.setSummary(DEUTSCH);
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
