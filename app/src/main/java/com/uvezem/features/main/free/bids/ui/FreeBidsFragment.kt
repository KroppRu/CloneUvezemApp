package com.uvezem.features.main.free.bids.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.domain.BidsInteractor
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.features.main.free.bids.presenter.FreeBidsAdapter
import com.uvezem.features.main.free.bids.presenter.FreeBidsPresenter
import com.uvezem.features.prefs.Preference
import kotlinx.android.synthetic.main.free_bids_fragment.*

class FreeBidsFragment : Fragment(), FreeBidsView {

    private lateinit var presenter: FreeBidsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initDependency()
        return inflater.inflate(R.layout.free_bids_fragment, container, false)
    }

    private fun initDependency() {
        val bidsRepository = BidsRepository(App.apiRetrofit, Preference())
        val bidsInteractor = BidsInteractorImpl(bidsRepository)
        presenter = FreeBidsPresenter(this, bidsInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        free_bid_rv.layoutManager = layoutManager

        presenter.loadData()
//        val adapter = FreeBidsAdapter(generateFake())
//
//        free_bid_rv.adapter = adapter
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }
}