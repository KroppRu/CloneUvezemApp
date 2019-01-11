package com.uvezem.features.offer.ui

interface NewOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)
}