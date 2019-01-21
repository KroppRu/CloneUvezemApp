package com.uvezem.features.main.free.bids.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.features.main.free.bids.presenter.FreeBidsAdapter
import com.uvezem.features.main.free.bids.presenter.FreeBidsPresenter
import com.uvezem.features.offer.newoffer.ui.NewOfferFragment.Companion.BID_ID_KEY
import kotlinx.android.synthetic.main.free_bids_fragment.*

class FreeBidsFragment : Fragment(), FreeBidsView {

    private lateinit var presenter: FreeBidsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.free_bids_fragment, container, false)
    }

    private fun initDependency() {
        val bidsRepository = BidsRepository(App.apiRetrofit, Preference())
        val bidsInteractor = BidsInteractorImpl(bidsRepository)
        val adapter = FreeBidsAdapter(listOf())
        free_bid_rv.adapter = adapter
        presenter = FreeBidsPresenter(this, bidsInteractor, adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        free_bid_rv.layoutManager = layoutManager
        initDependency()
        presenter.loadData()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun openFillOrderFragment(bidId: Int) {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)

        val bundle = Bundle()
        bundle.putInt(BID_ID_KEY, bidId)

        navController.navigate(R.id.newOfferFragment, bundle)
    }
}