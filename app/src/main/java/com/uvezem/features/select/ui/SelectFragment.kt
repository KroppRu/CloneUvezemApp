package com.uvezem.features.select.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        }

    }
}