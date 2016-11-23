package com.lentach.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.lentach.R;

/**
 * Created by Илья on 20.07.2016.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
