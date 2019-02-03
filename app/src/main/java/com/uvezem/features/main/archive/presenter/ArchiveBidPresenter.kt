package com.uvezem.features.main.archive.presenter

import android.util.Log
import com.uvezem.BasePresenter
import com.uvezem.domain.BidsInteractor
import com.uvezem.features.main.archive.ui.ArchiveBidView
import com.uvezem.model.Deliveries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ArchiveBidPresenter(
    private val view: ArchiveBidView,
    private val bidsInteractor: BidsInteractor,
    private val bidsAdapter: ArchiveBidsAdapter
): BasePresenter() {

    companion object {
        private const val TAG = "MyBidPresenter"
    }

    private lateinit var deliveries: Deliveries

    fun loadData() {
        bidsInteractor.loadArchiveBids()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::loadBidsOnError,
                onSuccess = ::loadBidsOnSuccess
            ).addTo(disposable)
    }


    private fun loadBidsOnError(t: Throwable) {
        Log.d(TAG, t.message)
        view.hideProgress()
        t.message?.let { view.showError(it) }
    }

    private fun loadBidsOnSuccess(deliveries: Deliveries) {
        Log.d(TAG, deliveries.toString())
        view.hideProgress()
        deliveries.bids?.let {
            bidsAdapter.updateDeliveries(it)
        }
        this.deliveries = deliveries
    }
}