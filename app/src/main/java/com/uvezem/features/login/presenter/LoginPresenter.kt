package com.uvezem.features.login.presenter

import com.uvezem.features.login.domain.LoginInteractor
import com.uvezem.features.login.ui.LoginView
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class LoginPresenter constructor(
    private val view: LoginView,
    private val loginInteractor: LoginInteractor
) {

    fun onLoginBtnClick(login: String, pass: String) {
        loginInteractor.loginUser(login, pass)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { view.showProgress() }
            .subscribe(on)
    }

    fun prepareUserData() {
        val userApp = loginInteractor.getUserApp()
        userApp?.let {
            view.next()
        }
    }
}