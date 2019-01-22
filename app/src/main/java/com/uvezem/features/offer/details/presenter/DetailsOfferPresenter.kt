package com.uvezem.features.offer.details.presenter

import com.uvezem.Constans.Companion.EMPTY_STRING
import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.details.ui.DetailsOfferView
import com.uvezem.model.CompanyDetail
import com.uvezem.model.Driver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class DetailsOfferPresenter(
    private val view: DetailsOfferView,
    private val offerInteractor: OfferInteractor
) {

    private var companyId: Int? = null
    private var orderId: Int? = null

    private lateinit var companyDetail: CompanyDetail
    private var selectedDriver: Driver? = null

    fun prepareData(companyId: Int, orderId: Int) {
        this.companyId = companyId
        this.orderId = orderId

        offerInteractor.loadCompanyData(companyId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::prepareDataOnError,
                onSuccess = ::prepareDataOnSuccess
            )
    }

    private fun prepareDataOnSuccess(companyDetail: CompanyDetail) {
        this.companyDetail = companyDetail
        view.setDriversSelectList(companyDetail.drivers)
    }

    private fun prepareDataOnError(t: Throwable) {
        t.message?.let {
            view.showError(it)
        }
    }

    fun onDriverSelect(position: Int) {
        selectedDriver = companyDetail.drivers[position]
        selectedDriver?.let {
            it.trail?.let { trail -> view.setTrailName(trail.name) } ?: view.setTrailName(EMPTY_STRING)
            it.truck?.let { truck -> view.setTruckName(truck.name) } ?: view.setTruckName(EMPTY_STRING)
        }
    }

    fun onAttachBtnClick() {
        if (selectedDriver != null
            && selectedDriver!!.truck != null
            && selectedDriver!!.trail != null
            && orderId != null
        ) {
            offerInteractor.attachInfo(
                orderId!!,
                selectedDriver!!.id,
                selectedDriver!!.truck!!.id,
                selectedDriver!!.trail!!.id
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress() }
                .subscribeBy(
                    onError = ::attachInfoOnError,
                    onComplete = ::attachInfoOnComplete
                )
        } else {
            view.showError("Необходимо заполнить все данные")
        }
    }

    private fun attachInfoOnComplete() {
        view.showMessage("Заявка успешно дополнена, позвоните логисту для уточнения деталей")
        view.navigateToHome()
    }

    private fun attachInfoOnError(t: Throwable) {
        t.message?.let {
            view.showError(it)
        }
        view.navigateToHome()
    }

}