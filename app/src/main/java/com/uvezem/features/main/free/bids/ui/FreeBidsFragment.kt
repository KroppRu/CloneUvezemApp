package com.uvezem.features.main.free.bids.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.R
import com.uvezem.features.main.free.bids.presenter.FreeBidsAdapter
import kotlinx.android.synthetic.main.free_bids_fragment.*

class FreeBidsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.free_bids_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        free_bid_rv.layoutManager = layoutManager
        val adapter = FreeBidsAdapter(generateFake())

        free_bid_rv.adapter = adapter
    }
}