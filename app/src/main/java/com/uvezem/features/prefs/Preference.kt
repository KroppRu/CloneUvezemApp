package com.uvezem.features.prefs

import android.content.SharedPreferences
import com.uvezem.App

class Preference {

    companion object {
        private const val PREFS_NAME = "com.uvezem.prefs"
    }

    private var preferences: SharedPreferences

    init {
        preferences = App.instance.getSharedPreferences(PREFS_NAME, 0)
    }
}