package com.uvezem.features.main.my.bids.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.features.main.my.bids.presenter.MyBidAdapter
import com.uvezem.features.main.my.bids.presenter.MyBidPresenter
import com.uvezem.features.offer.details.ui.DetailsOfferFragment
import kotlinx.android.synthetic.main.bids_layout.*

class MyBidFragment: Fragment() , MyBidView{

    private lateinit var navController: NavController
    private lateinit var presenter: MyBidPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)
        return inflater.inflate(R.layout.bids_layout, container, false)
    }

    private fun initDependency() {
        val bidsRepository = BidsRepository(App.apiRetrofit, Preference())
        val bidsInteractor = BidsInteractorImpl(bidsRepository)
        val adapter = MyBidAdapter(listOf())
        rv.adapter = adapter
        presenter = MyBidPresenter(this, bidsInteractor, adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = layoutManager
        initDependency()
        presenter.loadData()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        rv.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
        rv.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun openApplyDataFragment(orderId: Int, companyId: Int) {
        val bundle = Bundle()
        bundle.putInt(DetailsOfferFragment.DETAILS_COMPANY_ID_KEY, companyId)
        bundle.putInt(DetailsOfferFragment.DETAILS_ORDER_ID_KEY, orderId)

        navController.navigate(R.id.detailsOfferFragment, bundle)
    }
}