package com.uvezem.features.offer.newoffer.presenter

import android.app.DatePickerDialog
import android.widget.DatePicker
import com.uvezem.domain.BidsInteractor
import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.newoffer.ui.NewOfferView
import com.uvezem.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*

class NewOfferPresenter(
    private val view: NewOfferView,
    private val offerInteractor: OfferInteractor,
    private val bidsInteractor: BidsInteractor
) : DatePickerDialog.OnDateSetListener {

    private lateinit var companys: List<Company>
    private lateinit var bid: DeliveriesItem

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    private var selectedCompany: Company? = null
    private var selectedPerson: Person? = null
    private var amount: Int? = null
    private var calendar = Calendar.getInstance(Locale.ENGLISH)

    fun prepareDataForFilling(bidId: Int) {
        Singles.zip(offerInteractor.loadCompanyByUser(), bidsInteractor.loadBid(bidId))
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showProgress() }
            .subscribeBy(
                onError = ::prepareDataOnError,
                onSuccess = ::prepareDataOnSuccess
            )
    }

    private fun prepareDataOnSuccess(dataPair: Pair<List<Company>, DeliveriesItem>) {
        view.hideProgress()
        companys = dataPair.first
        bid = dataPair.second

        view.setCompanySelectList(companys)

        amount = bid.priceDelivery
        view.setAmount(bid.priceDelivery.toString())

        calendar.time = dateFormat.parse(bid.dateShipment)
        view.setDate(bid.dateShipment)
        setDatePick()
    }

    private fun prepareDataOnError(t: Throwable) {
        view.hideProgress()
        t.message?.let {
            view.showError(it)
        }
    }

    fun onCompanySelect(position: Int) {
        selectedCompany = companys[position]
        selectedPerson = null
        view.setPersonSelectList(selectedCompany!!.persons)
    }

    fun onPersonSelect(position: Int) {
        selectedPerson = selectedCompany?.persons?.get(position)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        calendar.set(Calendar.YEAR, p1)
        calendar.set(Calendar.MONTH, p2)
        calendar.set(Calendar.DAY_OF_MONTH, p3)
        view.setDate(dateFormat.format(calendar.time))
        setDatePick()
    }

    private fun setDatePick() {
        view.setDatePicker(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun onAmountValueChanged(amountString: String) {
        amount = if (amountString.isBlank()) {
            null
        } else {
            amountString.toInt()
        }
    }

    fun onCreateOfferButtonClick() {
        if (amount != null
            && selectedCompany != null
            && selectedPerson != null
        ) {
            offerInteractor.createOffer(
                bid.id,
                selectedCompany!!,
                selectedPerson!!,
                amount!!,
                dateFormat.format(calendar.time)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress() }
                .subscribeBy(
                    onError = ::onCreateofferError,
                    onSuccess = ::onCreateOfferSuccess
                )
        } else {
            view.showError("Необходимо заполнить все данные")
        }
    }

    private fun onCreateOfferSuccess(order: Order) {
        //view.hideProgress()
        when (order.status) {
            OrderStatus.APPROVED -> view.navigateToDetails(order.id, selectedCompany!!.id)
            OrderStatus.NEED_APPROVE -> view.navigateToHome()
        }
    }

    private fun onCreateofferError(t: Throwable) {
        view.hideProgress()
        t.message?.let {
            view.showError(it)
        }
        view.navigateToHome()
    }
}