package com.uvezem.domain

import com.uvezem.features.prefs.Preference
import com.uvezem.model.UserApp

interface UserInteractor {

    fun getUserApp(): UserApp?

    fun saveUserApp(userApp: UserApp)
}

class UserInteractorImpl (private val preference: Preference): UserInteractor {

    override fun getUserApp(): UserApp? =
        preference.getUserApp()

    override fun saveUserApp(userApp: UserApp) {
        preference.saveUserApp(userApp)
    }
}