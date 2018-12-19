package com.uvezem.features.login.ui

interface LoginView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun next()
}