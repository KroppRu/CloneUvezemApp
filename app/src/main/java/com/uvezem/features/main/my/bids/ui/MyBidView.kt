package com.uvezem.features.main.my.bids.ui

interface MyBidView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun openApplyDataFragment(orderId: Int, companyId: Int)
}