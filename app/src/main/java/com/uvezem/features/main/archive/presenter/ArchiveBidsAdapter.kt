package com.uvezem.features.main.archive.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uvezem.Constans.Companion.EMPTY_STRING
import com.uvezem.R
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.DeliveryPoint

class ArchiveBidsAdapter(private var deliveries: List<DeliveriesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val COMPACT_TYPE = 0
        private const val FULL_TYPE = 1
    }

    private var clickedItems: Array<Boolean>

    fun updateDeliveries(deliveries: List<DeliveriesItem>) {
        this.deliveries = deliveries
        clickedItems = Array(deliveries.size) { false }
        notifyDataSetChanged()
    }

    init {
        clickedItems = Array(deliveries.size) { false }
    }

    private fun updateElement(position: Int) {
        clickedItems[position] = !clickedItems[position]
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == COMPACT_TYPE) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.archive_bid_compact_item, parent, false)
            ArchiveBidViewHolderCompact(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.archive_bid_full_item, parent, false)
            ArchiveBidViewHolderFull(itemView)
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
            is ArchiveBidViewHolderCompact -> {
                holder.elementClickListener = { updateElement(it) }
                holder.dateTextView?.text = delivery.dateShipment
                holder.summTextView?.text = delivery.priceDelivery.toString()
                holder.loadPlaceTextView?.text = delivery.addressWarehouse
                holder.deliveryPlaceTextView?.text = getDeliveryDestination(delivery.deliveryPoints)
            }

            is ArchiveBidViewHolderFull -> {
                holder.elementClickListener = { updateElement(it) }
                holder.dateTextView?.text = delivery.dateShipment
                holder.summTextView?.text = delivery.priceDelivery.toString()
                holder.loadPlaceTextView?.text = delivery.addressWarehouse
                holder.deliveryPlaceTextView?.text = getAllDeliveryDestinations(delivery.deliveryPoints)
                holder.typeTextView?.text = delivery.cargoType
                holder.veightTextView?.text = delivery.weight.toString()
                holder.volumeTextView?.text = delivery.volume.toString()
                holder.logistPhoneTextView?.text = delivery.logistPhone
                holder.refTextView?.text = if (delivery.refrigerator) {
                    "Рефрижератор"
                } else {
                    "Тент"
                }
            }
        }
    }

    private fun getDeliveryDestination(points: List<DeliveryPoint>?): String {
        val lastPoint = points?.lastOrNull()

        return lastPoint?.city ?: EMPTY_STRING
    }

    private fun getAllDeliveryDestinations(points: List<DeliveryPoint>?): String {
        var destinationsPoints: String = EMPTY_STRING
        points?.forEach {
            destinationsPoints += "${it.address}\n"
        }
        return destinationsPoints
    }
}