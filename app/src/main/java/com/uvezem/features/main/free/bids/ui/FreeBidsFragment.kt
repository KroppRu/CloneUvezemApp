package com.uvezem.features.main.free.bids.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.BidsRepository
import com.uvezem.data.prefs.Preference
import com.uvezem.domain.BidsInteractorImpl
import com.uvezem.features.main.HomeActivity
import com.uvezem.features.main.free.bids.presenter.FreeBidsAdapter
import com.uvezem.features.main.free.bids.presenter.FreeBidsPresenter
import com.uvezem.features.offer.newoffer.ui.NewOfferFragment.Companion.BID_ID_KEY
import kotlinx.android.synthetic.main.bids_layout.*


class FreeBidsFragment : Fragment(), FreeBidsView {

    private lateinit var navController: NavController
    private lateinit var presenter: FreeBidsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bids_layout, container, false)
    }

    private fun initDependency() {
        val bidsRepository = BidsRepository(App.apiRetrofit, Preference())
        val bidsInteractor = BidsInteractorImpl(bidsRepository)
        val adapter = FreeBidsAdapter(listOf())
        rv.adapter = adapter
        presenter = FreeBidsPresenter(this, bidsInteractor, adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = layoutManager
        initDependency()
        presenter.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.mi_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView?
        searchView?.apply {
            val queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    presenter.onSearchTextChange(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    (activity as HomeActivity).closeKeyboard()
                    return true
                }
            }
            setOnQueryTextListener(queryTextListener)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
        rv.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_bar?.visibility = View.GONE
        rv?.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun openFillOrderFragment(bidId: Int) {
        val bundle = Bundle()
        bundle.putInt(BID_ID_KEY, bidId)

        navController.navigate(R.id.newOfferFragment, bundle)
    }

    override fun onDestroyView() {
        presenter.dispose()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.logoutBtn) {
            presenter.logout()
        }
        return true
    }

    override fun navigateToLogin() {
        navController.popBackStack(R.id.loginFragment, false)
    }
}