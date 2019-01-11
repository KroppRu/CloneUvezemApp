package com.uvezem.features.main.free.bids.presenter

import android.util.Log
import com.uvezem.domain.BidsInteractor
import com.uvezem.features.main.free.bids.ui.FreeBidsView
import com.uvezem.model.Deliveries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FreeBidsPresenter(
    private val view: FreeBidsView,
    private val bidsInteractor: BidsInteractor,
    private val bidsAdapter: FreeBidsAdapter
) {

    companion object {
        private const val TAG = "FreeBidsPresenter"
    }

    init {
        bidsAdapter.btnFillOrderClickListener = ::listItemClick
    }

    fun loadData() {
        loadBids()
    }

    private fun listItemClick(bidId: Int) {
        view.openFillOrderFragment(bidId)
    }

    private fun loadBids() {
        bidsInteractor.loadFreeBids()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::loadBidsOnError,
                onSuccess = ::loadBidsOnSuccess
            )
    }

    private fun loadBidsOnError(t: Throwable) {
        Log.d(TAG, t.message)
        t.message?.let { view.showError(it) }
    }

    private fun loadBidsOnSuccess(deliveries: Deliveries) {
        Log.d(TAG, deliveries.toString())
        deliveries.deliveries?.let {
            bidsAdapter.updateDeliveries(it)
        }
    }
}