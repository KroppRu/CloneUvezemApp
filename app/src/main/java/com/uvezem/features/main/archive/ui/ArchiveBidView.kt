package com.uvezem.features.main.archive.ui

interface ArchiveBidView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun openApplyDataFragment(orderId: Int, companyId: Int)
}