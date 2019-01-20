package com.uvezem.features.offer.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.OfferRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.domain.OfferInteractorImpl
import com.uvezem.features.offer.presenter.NewOfferPresenter
import com.uvezem.model.Company
import com.uvezem.model.Person
import kotlinx.android.synthetic.main.new_offer_fragment.*

class NewOfferFragment : Fragment(), NewOfferView {

    companion object {
        const val BID_ID_KEY = "NewOfferFragment.bid.id.key"
    }

    private lateinit var presenter: NewOfferPresenter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)

        initDependency()
        return inflater.inflate(R.layout.new_offer_fragment, container, false)
    }

    private fun initDependency() {
        val prefs = Preference()
        val offerRepository = OfferRepository(App.apiRetrofit, prefs)
        val offerInteractor = OfferInteractorImpl(offerRepository)
        val bidsRepository = BidsRepository(App.apiRetrofit, prefs)
        val bidsInteractor = BidsInteractorImpl(bidsRepository)
        presenter = NewOfferPresenter(this, offerInteractor, bidsInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getInt(BID_ID_KEY)?.let {
            presenter.prepareDataForFilling(it)
        }
    }

    override fun setDatePicker(year: Int, month: Int, day: Int) {
        dateEditText.setOnClickListener {
            DatePickerDialog(context!!, presenter, year, month, day)
                //.datePicker.minDate = 1111L
                .show()
        }
    }

    override fun onDestroyView() {
        Log.d("NewOfferFragment", "DESTROY")
        super.onDestroyView()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }

    override fun setAmount(amount: String) {
        amountEditText.editText?.setText(amount)
    }

    override fun setDate(date: String) {
        dateEditText.setText(date)
    }

    override fun setCompanySelectList(companies: List<Company>) {
        val spinnerAdapter = ArrayAdapter<Company>(
            context!!,
            R.layout.custom_spinner_item,
            companies
        )
        company.adapter = spinnerAdapter
        company.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //not supported
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                person.adapter = null
                presenter.onCompanySelect(position)
            }
        }
    }

    override fun setPersonSelectList(persons: List<Person>) {
        val spinnerAdapter = ArrayAdapter<Person>(
            context!!,
            R.layout.custom_spinner_item,
            persons
        )

        person.adapter = spinnerAdapter
        person.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //not supported
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onPersonSelect(position)
            }
        }
    }
}