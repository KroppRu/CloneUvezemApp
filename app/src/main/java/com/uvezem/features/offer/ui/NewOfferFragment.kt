package com.uvezem.features.offer.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.R
import com.uvezem.features.offer.presenter.NewOfferPresenter

class NewOfferFragment:  Fragment(), NewOfferView {

    companion object {
        const val BID_ID_KEY = "NewOfferFragment.bid.id.key"
    }

    private lateinit var presenter: NewOfferPresenter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //initDependency()
        return inflater.inflate(R.layout.new_offer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }
}