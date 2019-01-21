package com.uvezem.features.offer.details.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.OfferRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.OfferInteractorImpl
import com.uvezem.features.offer.details.presenter.DetailsOfferPresenter

class DetailsOfferFragment : Fragment(), DetailsOfferView {

    companion object {
        const val DETAILS_COMPANY_ID_KEY = "DetailsOfferFragment.company.id.key"
        const val DETAILS_ORDER_ID_KEY = "DetailsOfferFragment.order.id.key"
    }

    private var companyId: Int? = null
    private var orderId: Int? = null

    private lateinit var navController: NavController
    private lateinit var presenter: DetailsOfferPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)
        initDependency()
        return inflater.inflate(R.layout.details_offer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getInt(DETAILS_COMPANY_ID_KEY)?.let {
            companyId = it
            presenter.prepareData(it)
        } ?: throw Throwable("DETAILS_COMPANY_ID_KEY is null")

        arguments?.getInt(DETAILS_ORDER_ID_KEY)?.let {
            orderId = it
        } ?: throw Throwable("DETAILS_ORDER_ID_KEY is null")


    }

    private fun initDependency() {
        val prefs = Preference()
        val offerRepository = OfferRepository(App.apiRetrofit, prefs)
        val offerInteractor = OfferInteractorImpl(offerRepository)
        presenter = DetailsOfferPresenter(this, offerInteractor)
    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}