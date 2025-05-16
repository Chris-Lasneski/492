package com.example.roomyweather.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.MultiSelectListPreference
import com.example.roomyweather.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val languagePref: MultiSelectListPreference? = findPreference(
            getString(R.string.pref_units_title)
        )
    }
}