package com.uvezem.features.offer.details.ui

import com.uvezem.model.Driver

interface DetailsOfferView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun showMessage(message: String)

    fun setDriversSelectList(drivers: List<Driver>)

    fun setDefaultData()

    fun setTruckName(truckName: String)

    fun setTrailName(trailName: String)

    fun navigateToHome()
}