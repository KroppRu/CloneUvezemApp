package com.uvezem.features.offer.presenter

import android.widget.ArrayAdapter
import com.uvezem.domain.BidsInteractor
import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.ui.NewOfferView
import com.uvezem.model.Company
import com.uvezem.model.DeliveriesItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.subscribeBy

class NewOfferPresenter(
    private val view: NewOfferView,
    private val offerInteractor: OfferInteractor,
    private val bidsInteractor: BidsInteractor
) {

    private lateinit var companys: List<Company>
    private lateinit var bid: DeliveriesItem

    private var selectedCompany: Company? = null

    fun prepareDataForFilling(bidId: Int) {
        Singles.zip(offerInteractor.loadCompanyData(), bidsInteractor.loadBid(bidId))
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::prepareDataOnError,
                onSuccess = ::prepareDataOnSuccess
            )
    }

    private fun prepareDataOnSuccess(dataPair: Pair<List<Company>, DeliveriesItem>) {
        companys = dataPair.first
        bid = dataPair.second
        view.setCompanySelectList(companys)
        view.setAmount(bid.priceDelivery.toString())
        view.setDate(bid.dateShipment)
    }

    private fun prepareDataOnError(t: Throwable) {

    }

    fun onPersonClick() {
        //view.openCompanySelectFragment(selectedCompany?.persons?.map { it.name })
    }
}