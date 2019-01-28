package com.uvezem.features.offer.newoffer.ui

import com.uvezem.model.Company
import com.uvezem.model.Person

interface NewOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun setAmount(amount: String)

    fun setCompanySelectList(companies: List<Company>)

    fun setPersonSelectList(persons: List<Person>)

    fun setDate(date: String)

    fun setDatePicker(year: Int, month: Int, day: Int)

    fun navigateToDetails(orderId: Int, companyId: Int)

    fun navigateToHome()
}