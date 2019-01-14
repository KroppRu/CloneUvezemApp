package com.uvezem.features.offer.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.uvezem.features.select.ui.SelectFragment.Companion.DATA_LIST_KEY
import kotlinx.android.synthetic.main.new_offer_fragment.*
import java.util.ArrayList

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
        companyEditText.setOnClickListener { presenter.onCompanyClick() }
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
        dateEditText.editText?.setText(date)
    }

    override fun openCompanySelectFragment(companies: List<String>) {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)

        val bundle = Bundle()
        bundle.putStringArrayList(DATA_LIST_KEY, companies as ArrayList<String>)

        navController.navigate(R.id.selectFragment, bundle)
    }
}