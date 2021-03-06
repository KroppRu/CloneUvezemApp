package com.uvezem.features.main.free.bids.presenter

import android.util.Log
import com.uvezem.BasePresenter
import com.uvezem.domain.BidsInteractor
import com.uvezem.features.main.free.bids.ui.FreeBidsView
import com.uvezem.model.Deliveries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class FreeBidsPresenter(
    private val view: FreeBidsView,
    private val bidsInteractor: BidsInteractor,
    private val bidsAdapter: FreeBidsAdapter
) : BasePresenter() {

    companion object {
        private const val TAG = "FreeBidsPresenter"
    }

    private var deliveries: Deliveries? = null

    init {
        bidsAdapter.btnFillOrderClickListener = ::listItemClick
        bidsAdapter.btnCancelClickListener = ::btnCancelOnClick
    }

    fun loadData() {
        loadBids()
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
            ).addTo(disposable)
    }

    private fun loadBidsOnError(t: Throwable) {
        Log.d(TAG, t.message)
        view.hideProgress()
        t.message?.let { view.showError(it) }
    }

    private fun loadBidsOnSuccess(deliveries: Deliveries) {
        view.hideProgress()
        deliveries.bids?.let {
            bidsAdapter.updateDeliveries(it)
        }
        this.deliveries = deliveries
    }

    fun onSearchTextChange(searchText: String) {
        val filteredList = deliveries?.bids?.filter { bid ->
            val filteredPoint = bid.deliveryPoints?.firstOrNull {
                it.city.contains(searchText, true)
            }
            val warehouseContainsFilter = bid.addressWarehouse?.contains(searchText, true)

            warehouseContainsFilter ?: false || filteredPoint != null
        }
        filteredList?.let {
            bidsAdapter.updateDeliveries(it)
        }
    }

    fun logout() {
        //TODO убрать пользователя из шары
        view.navigateToLogin()
    }
}