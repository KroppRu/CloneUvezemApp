package com.uvezem.features.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import com.uvezem.App
import com.uvezem.model.UserApp


class Preference {

    companion object {
        private const val PREFS_NAME = "com.uvezem.prefs"
        private const val USER_APP_KEY = "com.uvezem.prefs.userAppKey"
    }

    private var preferences: SharedPreferences
    private val gson = Gson()

    init {
        preferences = App.instance.getSharedPreferences(PREFS_NAME, 0)
    }

    fun getUserApp(): UserApp? {
        val userData = preferences.getString(USER_APP_KEY, null)
        return gson.fromJson(userData, UserApp::class.java)
    }

    fun saveUserApp(userApp: UserApp) {
        preferences.edit().apply {
            putString(USER_APP_KEY, gson.toJson(userApp))
            apply()
        }
    }
}