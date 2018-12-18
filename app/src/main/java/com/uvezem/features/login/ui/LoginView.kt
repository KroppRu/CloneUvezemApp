package com.uvezem.features.login.ui

import com.uvezem.model.UserApp

interface LoginView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun next()
}