package com.uvezem.features.offer.details.presenter

import android.util.Log
import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.details.ui.DetailsOfferView
import com.uvezem.features.offer.newoffer.ui.NewOfferView
import com.uvezem.model.CompanyDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class DetailsOfferPresenter(
    private val view: DetailsOfferView,
    private val offerInteractor: OfferInteractor
) {

    fun prepareData(companyId: Int) {
        offerInteractor.loadCompanyData(companyId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::prepareDataOnError,
                onSuccess = ::prepareDataOnSuccess
            )
    }

    private fun prepareDataOnSuccess(companyDetail: CompanyDetail) {
        Log.d("prepareDataOnSuccess", companyDetail.toString())
    }

    private fun prepareDataOnError(t: Throwable) {
        t.message?.let {
            view.showError(it)
        }
    }

}