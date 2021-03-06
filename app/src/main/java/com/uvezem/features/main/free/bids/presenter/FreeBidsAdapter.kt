package com.uvezem.features.main.free.bids.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.Constans.Companion.EMPTY_STRING
import com.uvezem.R
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.DeliveryPoint

class FreeBidsAdapter(private var deliveries: List<DeliveriesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var btnFillOrderClickListener: ((Int) -> Unit)? = null
    var btnCancelClickListener: ((Int) -> Unit)? = null

    companion object {
        private const val COMPACT_TYPE = 0
        private const val FULL_TYPE = 1
    }

    private var clickedItems: Array<Boolean>

    //TODO
//    fun addDeliveries(deliveries: List<DeliveriesItem>) {
//        this.deliveries = deliveries
//        val newClickedArray = Array(deliveries.size) { index ->
//            clickedItems.getOrElse(index) { false }
//        }
//        clickedItems = newClickedArray
//        notifyDataSetChanged()
//    }

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
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bid_compact_item, parent, false)
            FreeBidViewHolderCompact(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bid_full_item, parent, false)
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
                holder.elementClickListener = { updateElement(it) }
                holder.dateTextView?.text = delivery.dateShipment
                holder.summTextView?.text = delivery.priceDelivery.toString()
                holder.loadPlaceTextView?.text = delivery.addressWarehouse
                holder.deliveryPlaceTextView?.text = getDeliveryDestination(delivery.deliveryPoints)

                if (delivery.carrier == null) {
                    holder.cancelButton?.visibility = View.GONE
                } else {
                    holder.cancelButton?.setOnClickListener {
                        btnCancelClickListener?.invoke(delivery.carrier.order.id)
                    }
                    holder.cancelButton?.visibility = View.VISIBLE
                }
            }

            is FreeBidViewHolderFull -> {
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
                if (delivery.carrier == null) {
                    holder.cancelButton?.visibility = View.GONE
                    holder.newOfferButton?.visibility = View.VISIBLE
                    holder.newOfferButton?.setOnClickListener {
                        btnFillOrderClickListener?.invoke(delivery.id)
                    }
                } else {
                    holder.cancelButton?.setOnClickListener {
                        btnCancelClickListener?.invoke(delivery.carrier.order.id)
                    }
                    holder.cancelButton?.visibility = View.VISIBLE
                    holder.newOfferButton?.visibility = View.GONE
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