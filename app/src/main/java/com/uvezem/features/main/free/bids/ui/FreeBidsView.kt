package com.uvezem.features.main.free.bids.ui

interface FreeBidsView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun openFillOrderFragment(bidId: Int)
}