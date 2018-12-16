package com.uvezem.features.login.ui

import com.uvezem.model.UserApp

interface LoginView {

    fun setUserData(userApp: UserApp)

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)
}