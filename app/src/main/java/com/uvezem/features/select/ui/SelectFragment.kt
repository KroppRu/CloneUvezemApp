package com.uvezem.features.select.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.uvezem.R
import com.uvezem.features.select.presenter.SearchAdapter
import kotlinx.android.synthetic.main.select_fragment.*

class SelectFragment : Fragment() {

    companion object {
        const val DATA_LIST_KEY = "SelectFragment.data.key.list"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.select_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        searchList.layoutManager = layoutManager

        val data = arguments?.getStringArrayList(DATA_LIST_KEY)
        data?.let {
            val adapter = SearchAdapter(it)
            searchList.adapter = adapter
            adapter.elementClickListener = ::onAdapterElementClick
        }

    }

    private fun onAdapterElementClick(position: Int) {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_home_fragment)
        navController.popBackStack()

        //activity?.setResult(Activity.RESULT_OK, Intent().putExtra("bla", position))
        onActivityResult(Activity.RESULT_OK, 0, Intent().putExtra("bla", position))
    }
}