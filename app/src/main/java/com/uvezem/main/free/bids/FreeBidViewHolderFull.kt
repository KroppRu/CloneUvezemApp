package com.uvezem.main.free.bids

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.uvezem.R

class FreeBidViewHolderFull(val parent: View) : RecyclerView.ViewHolder(parent) {
    var dateTextView: TextView? = null
    var summTextView: TextView? = null
    var loadPlaceTextView: TextView? = null
    var deliveryPlaceTextView: TextView? = null
    var typeTextView: TextView? = null
    var refTextView: TextView? = null
    var veightTextView: TextView? = null
    var volumeTextView: TextView? = null
    var elementClickListener: ((Int) -> Unit)? = null

    init {
        dateTextView = parent.findViewById(R.id.date_text_view)
        summTextView = parent.findViewById(R.id.summValue)
        loadPlaceTextView = parent.findViewById(R.id.loadPlaceValue)
        deliveryPlaceTextView = parent.findViewById(R.id.deliveryPlaceValue)
        typeTextView = parent.findViewById(R.id.typeValue)
        refTextView = parent.findViewById(R.id.refValue)
        veightTextView = parent.findViewById(R.id.veightValue)
        volumeTextView = parent.findViewById(R.id.volumeValue)
        parent.findViewById<View>(R.id.root_bid_full_item).setOnClickListener {
            elementClickListener?.invoke(adapterPosition)
        }
    }

}