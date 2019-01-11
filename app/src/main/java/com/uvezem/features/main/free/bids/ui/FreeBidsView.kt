package com.uvezem.features.main.free.bids.ui

import com.uvezem.model.Deliveries

interface FreeBidsView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun openFillOrderFragment(bidId: Int)
}