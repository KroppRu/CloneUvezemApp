package com.uvezem.features.main.my.bids.presenter

import android.util.Log
import com.uvezem.BasePresenter
import com.uvezem.domain.BidsInteractor
import com.uvezem.features.main.my.bids.ui.MyBidView
import com.uvezem.model.Deliveries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class MyBidPresenter(
    private val view: MyBidView,
    private val bidsInteractor: BidsInteractor,
    private val bidsAdapter: MyBidAdapter
): BasePresenter() {

    companion object {
        private const val TAG = "MyBidPresenter"
    }

    private lateinit var deliveries: Deliveries

    init {
        bidsAdapter.btnCancelClickListener = ::btnCancelOnClick
        bidsAdapter.btnApplyClickListener = ::btnApplyOnClick
    }

    private fun btnApplyOnClick(orderId: Int, compantId: Int) {
        view.openApplyDataFragment(orderId, compantId)
    }

    private fun btnCancelOnClick(orderId: Int) {
        bidsInteractor.cancelOrder(orderId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::cancelOrderOnError,
                onComplete = ::cancelOrderOnComplete
            ).addTo(disposable)
    }

    private fun cancelOrderOnError(t: Throwable) {
        view.hideProgress()
        t.message?.let {
            view.showError(it)
        }
    }

    private fun cancelOrderOnComplete() {
        loadBids()
    }

    fun loadData() {
        loadBids()
    }

    private fun loadBids() {
        bidsInteractor.loadMyBids()
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
        deliveries.deliveries?.let {
            bidsAdapter.updateDeliveries(it)
        }
        this.deliveries = deliveries
    }
}