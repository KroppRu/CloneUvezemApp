package com.uvezem.main.free.bids

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.R
import com.uvezem.model.Delivery
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

    private fun generateFake(): List<Delivery> {
        val d1 = Delivery(
            date = "20.02.2018",
            priceDelivery = 15000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d2 = Delivery(
            date = "20.02.2018",
            priceDelivery = 16000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d3 = Delivery(
            date = "20.02.2018",
            priceDelivery = 17000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d4 = Delivery(
            date = "20.02.2018",
            priceDelivery = 18000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d5 = Delivery(
            date = "20.02.2018",
            priceDelivery = 19000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d6 = Delivery(
            date = "20.02.2018",
            priceDelivery = 20000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d7 = Delivery(
            date = "20.02.2018",
            priceDelivery = 30000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d8 = Delivery(
            date = "20.02.2018",
            priceDelivery = 40000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )
        val d9 = Delivery(
            date = "20.02.2018",
            priceDelivery = 50000,
            addressWarehouse = "Красноярск",
            deliveryPlace = "Ачинск"
        )

        return listOf(d1, d2, d3, d4, d5, d6, d7, d8, d9)
    }
}