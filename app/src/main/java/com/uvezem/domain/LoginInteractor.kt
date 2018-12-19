package com.uvezem.domain

import com.uvezem.data.LoginRepository
import com.uvezem.features.prefs.Preference
import com.uvezem.model.UserApp
import io.reactivex.Single

interface LoginInteractor {

    fun loginUser(login: String, pass: String): Single<UserApp>
}

class LoginInteractorImpl(private val loginRepository: LoginRepository) : LoginInteractor {

    override fun loginUser(login: String, pass: String): Single<UserApp> =
        loginRepository.loginUser(login, pass)
}