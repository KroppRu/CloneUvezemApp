package com.uvezem.features.offer.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.OfferRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.domain.OfferInteractorImpl
import com.uvezem.features.offer.presenter.NewOfferPresenter

class NewOfferFragment : Fragment(), NewOfferView {

    companion object {
        const val BID_ID_KEY = "NewOfferFragment.bid.id.key"
    }

    private lateinit var presenter: NewOfferPresenter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }
}