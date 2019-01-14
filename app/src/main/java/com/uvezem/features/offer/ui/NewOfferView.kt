package com.uvezem.features.offer.ui

interface NewOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun setAmount(amount: String)

    fun setDate(date: String)

    fun openCompanySelectFragment(companies: List<String>)
}