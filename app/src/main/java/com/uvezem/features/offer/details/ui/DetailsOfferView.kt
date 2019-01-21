package com.uvezem.features.offer.details.ui

interface DetailsOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)
}