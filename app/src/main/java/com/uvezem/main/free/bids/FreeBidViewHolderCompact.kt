package com.uvezem.main.free.bids

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.uvezem.R

class FreeBidViewHolderCompact(val parent: View): RecyclerView.ViewHolder(parent) {
    var dateTextView: TextView? = null
    var summTextView: TextView? = null
    var loadPlaceTextView: TextView? = null
    var deliveryPlaceTextView: TextView? = null
    var elementClickListener: ((Int) -> Unit)? = null

    init {
        dateTextView = parent.findViewById(R.id.date_text_view)
        summTextView = parent.findViewById(R.id.summValue)
        loadPlaceTextView = parent.findViewById(R.id.loadPlaceValue)
        deliveryPlaceTextView = parent.findViewById(R.id.deliveryPlaceValue)
        parent.findViewById<View>(R.id.root_bid_compact_item).setOnClickListener {
            elementClickListener?.invoke(adapterPosition)
        }
    }

}