package com.uvezem.features.login.domain

import com.uvezem.data.LoginRepository
import com.uvezem.features.prefs.Preference
import com.uvezem.model.UserApp
import io.reactivex.Single

interface LoginInteractor {

    fun getUserApp(): UserApp?

    fun saveUserApp(userApp: UserApp)

    fun loginUser(login: String, pass: String): Single<UserApp>
}

class LoginInteractorImpl(
    private val loginRepository: LoginRepository,
    private val preference: Preference
) : LoginInteractor {

    override fun getUserApp(): UserApp? =
        preference.getUserApp()

    override fun saveUserApp(userApp: UserApp) {
        preference.saveUserApp(userApp)
    }

    override fun loginUser(login: String, pass: String): Single<UserApp> =
        loginRepository.loginUser(login, pass)
}