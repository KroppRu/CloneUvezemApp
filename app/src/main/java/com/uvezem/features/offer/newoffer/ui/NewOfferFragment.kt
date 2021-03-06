package com.uvezem.features.offer.newoffer.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.OfferRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.domain.OfferInteractorImpl
import com.uvezem.features.main.HomeActivity
import com.uvezem.features.offer.details.ui.DetailsOfferFragment.Companion.DETAILS_COMPANY_ID_KEY
import com.uvezem.features.offer.details.ui.DetailsOfferFragment.Companion.DETAILS_ORDER_ID_KEY
import com.uvezem.features.offer.newoffer.presenter.NewOfferPresenter
import com.uvezem.model.Company
import com.uvezem.model.Person
import kotlinx.android.synthetic.main.new_offer_fragment.*

class NewOfferFragment : Fragment(), NewOfferView {

    companion object {
        const val BID_ID_KEY = "NewOfferFragment.bid.id.key"
        private const val DEFAULT_SPINNER_ITEM_INDEX = 0
    }

    private lateinit var presenter: NewOfferPresenter
    private lateinit var navController: NavController

    private val amountTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            presenter.onAmountValueChanged(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //not supported
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //not supported
        }
    }


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
        createOfferButton.setOnClickListener { presenter.onCreateOfferButtonClick() }
        amountEditText.addTextChangedListener(amountTextWatcher)
    }

    override fun setDatePicker(year: Int, month: Int, day: Int) {
        val dialog = DatePickerDialog(context!!, presenter, year, month, day)
        dateEditText.setOnFocusChangeListener { _, focus ->
            if (focus) {
                (activity as HomeActivity).closeKeyboard()
                dialog.show()
            }
        }
        dateEditText.setOnClickListener {
            (activity as HomeActivity).closeKeyboard()
            dialog.show()
        }
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        dataLayout.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
        dataLayout.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun setAmount(amount: String) {
        amountEditText.setText(amount)
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
                (activity as HomeActivity).closeKeyboard()
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
                (activity as HomeActivity).closeKeyboard()
                presenter.onPersonSelect(position)
            }
        }
    }

    override fun navigateToDetails(orderId: Int, companyId: Int) {
        val bundle = Bundle()
        bundle.putInt(DETAILS_COMPANY_ID_KEY, companyId)
        bundle.putInt(DETAILS_ORDER_ID_KEY, orderId)

        navController.navigate(R.id.detailsOfferFragment, bundle)
    }

    override fun navigateToHome() {
        navController.popBackStack(R.id.freeBidsFragment, false)
    }

    override fun setDefaultCompany() {
        company.setSelection(DEFAULT_SPINNER_ITEM_INDEX)
        presenter.onCompanySelect(DEFAULT_SPINNER_ITEM_INDEX)
    }

    override fun setDefaultPerson() {
        person.setSelection(DEFAULT_SPINNER_ITEM_INDEX)
        presenter.onPersonSelect(DEFAULT_SPINNER_ITEM_INDEX)
    }

    override fun onDestroyView() {
        presenter.dispose()
        super.onDestroyView()
    }
}