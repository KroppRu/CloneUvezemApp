package com.uvezem.main.free.bids

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.R
import com.uvezem.model.Delivery

class FreeBidsAdapter(private var deliveries: List<Delivery>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: ClickListener? = null

    companion object {
        private val COMPACT_TYPE = 0
        private val FULL_TYPE = 1
    }

    private var clickedItems: Array<Boolean>

    interface ClickListener {
        fun onItemClick(v: View)
    }

    init {
        clickedItems = Array(deliveries.size) { _ -> false }
    }

    fun updateElement(position: Int) {
        clickedItems[position] = !clickedItems[position]
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == COMPACT_TYPE) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bid_compact_item, parent, false)
            itemView.setOnClickListener { _ -> listener?.onItemClick(itemView) }
            FreeBidViewHolderCompact(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bid_full_item, parent, false)
            itemView.setOnClickListener { _ -> listener?.onItemClick(itemView) }
            FreeBidViewHolderFull(itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (clickedItems[position]) {
            FULL_TYPE
        } else {
            COMPACT_TYPE
        }
    }

    override fun getItemCount(): Int =
        deliveries.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delivery = deliveries[position]
        when (holder) {
            is FreeBidViewHolderCompact -> {
                holder.dateTextView?.text = delivery.date
                holder.summTextView?.text = delivery.priceDelivery.toString()
                holder.loadPlaceTextView?.text = delivery.addressWarehouse
                holder.deliveryPlaceTextView?.text = delivery.deliveryPlace
            }

            is FreeBidViewHolderFull -> {
                holder.dateTextView?.text = delivery.date
                holder.summTextView?.text = delivery.priceDelivery.toString()
                holder.loadPlaceTextView?.text = delivery.addressWarehouse
                holder.deliveryPlaceTextView?.text = delivery.deliveryPlace
                holder.typeTextView?.text = delivery.cargoType
                holder.veightTextView?.text = delivery.weight.toString()
                holder.volumeTextView?.text = delivery.volume.toString()

                holder.refTextView?.text = if (delivery.refrigerator) {
                    "Рефрижератор"
                } else {
                    "Тент"
                }
            }
        }
    }
}