package com.uvezem.features.offer.details.ui

import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.uvezem.data.OfferRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.OfferInteractorImpl
import com.uvezem.features.main.HomeActivity
import com.uvezem.features.offer.details.presenter.DetailsOfferPresenter
import com.uvezem.model.Driver
import kotlinx.android.synthetic.main.details_offer_fragment.*

class DetailsOfferFragment : Fragment(), DetailsOfferView {

    companion object {
        const val DETAILS_COMPANY_ID_KEY = "DetailsOfferFragment.company.id.key"
        const val DETAILS_ORDER_ID_KEY = "DetailsOfferFragment.order.id.key"
        private const val DEFAULT_SPINNER_ITEM_INDEX = 0
    }


    private lateinit var navController: NavController
    private lateinit var presenter: DetailsOfferPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)
        initDependency()
        return inflater.inflate(R.layout.details_offer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var companyId = -1
        var orderId = -1

        arguments?.getInt(DETAILS_COMPANY_ID_KEY)?.let {
            companyId = it
        } ?: throw Throwable("DETAILS_COMPANY_ID_KEY is null")

        arguments?.getInt(DETAILS_ORDER_ID_KEY)?.let {
            orderId = it
        } ?: throw Throwable("DETAILS_ORDER_ID_KEY is null")

        presenter.prepareData(companyId, orderId)

        attachInfoButton.setOnClickListener { presenter.onAttachBtnClick() }
    }

    private fun initDependency() {
        val prefs = Preference()
        val offerRepository = OfferRepository(App.apiRetrofit, prefs)
        val offerInteractor = OfferInteractorImpl(offerRepository)
        presenter = DetailsOfferPresenter(this, offerInteractor)
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

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setDriversSelectList(drivers: List<Driver>) {
        val spinnerAdapter = ArrayAdapter<Driver>(
            context!!,
            R.layout.custom_spinner_item,
            drivers
        )
        driver.adapter = spinnerAdapter
        driver.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //not supported
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (activity as HomeActivity).closeKeyboard()
                presenter.onDriverSelect(position)
            }
        }
    }

    override fun setTruckName(truckName: String) {
        truckEditText.setText(truckName)
    }

    override fun setTrailName(trailName: String) {
        trailEditText.setText(trailName)
    }

    override fun navigateToHome() {
        navController.popBackStack(R.id.freeBidsFragment, false)
    }

    override fun onDestroyView() {
        presenter.dispose()
        super.onDestroyView()
    }

    override fun setDefaultData() {
        driver.setSelection(DEFAULT_SPINNER_ITEM_INDEX)
        presenter.onDriverSelect(DEFAULT_SPINNER_ITEM_INDEX)
    }
}