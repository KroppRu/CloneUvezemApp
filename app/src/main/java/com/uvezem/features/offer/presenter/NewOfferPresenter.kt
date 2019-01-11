package com.uvezem.features.offer.presenter

import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.ui.NewOfferView
import com.uvezem.model.Company
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class NewOfferPresenter(
    private val view: NewOfferView,
    private val offerInteractor: OfferInteractor
) {

    fun prepareDataForFilling() {
        offerInteractor.loadCompanyData()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::loadCompanyOnError,
                onSuccess = ::loadCompanyOnSuccess
            )
    }

    private fun loadCompanyOnSuccess(companyDataList: List<Company>) {

    }

    private fun loadCompanyOnError(t: Throwable) {

    }
}