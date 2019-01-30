package com.uvezem.features.login.presenter

import com.uvezem.BasePresenter
import com.uvezem.domain.LoginInteractor
import com.uvezem.domain.UserInteractor
import com.uvezem.features.login.ui.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter constructor(
    private val view: LoginView,
    private val loginInteractor: LoginInteractor,
    private val userInteractor: UserInteractor
) : BasePresenter() {

    fun onLoginBtnClick(login: String, pass: String) {
        loginInteractor.loginUser(login, pass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = { throwable ->
                    view.hideProgress()
                    throwable.message?.let {
                        view.showError(it)
                    }
                },
                onSuccess = {
                    userInteractor.saveUserApp(it)
                    view.hideProgress()
                    view.next()
                }
            ).addTo(disposable)
    }

    fun prepareUserData() {
        val userApp = userInteractor.getUserApp()
        userApp?.let {
            view.next()
        }
    }
}