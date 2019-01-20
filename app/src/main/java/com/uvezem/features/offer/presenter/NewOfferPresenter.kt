package com.uvezem.features.offer.presenter

import android.app.DatePickerDialog
import android.widget.DatePicker
import com.uvezem.domain.BidsInteractor
import com.uvezem.domain.OfferInteractor
import com.uvezem.features.offer.ui.NewOfferView
import com.uvezem.model.Company
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.Person
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

        amount = bid.priceDelivery
        view.setAmount(bid.priceDelivery.toString())

        calendar.time = dateFormat.parse(bid.dateShipment)
        view.setDate(bid.dateShipment)
        setDatePick()
    }

    private fun prepareDataOnError(t: Throwable) {
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
}