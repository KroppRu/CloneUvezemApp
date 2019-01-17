package com.uvezem.features.offer.ui

import com.uvezem.model.Company

interface NewOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun setAmount(amount: String)

    fun setCompanySelectList(companies: List<Company>)

    fun setDate(date: String)
}