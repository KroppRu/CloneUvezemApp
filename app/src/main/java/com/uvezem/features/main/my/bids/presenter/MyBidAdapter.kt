package com.uvezem.features.main.my.bids.presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uvezem.Constans
import com.uvezem.R
import com.uvezem.model.DeliveriesItem
import com.uvezem.model.DeliveryPoint
import com.uvezem.model.DeliveryStatus

class MyBidAdapter(private var deliveries: List<DeliveriesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var btnApplyClickListener: ((Int, Int) -> Unit)? = null
    var btnCancelClickListener: ((Int) -> Unit)? = null

    fun updateDeliveries(deliveries: List<DeliveriesItem>) {
        this.deliveries = deliveries
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        deliveries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_bid_item, parent, false)
        return MyBidViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder as MyBidViewHolder
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
        if (delivery.status == DeliveryStatus.FILLING) {
            holder.cancelButton?.visibility = View.VISIBLE
            holder.applyDriverButton?.visibility = View.VISIBLE
            holder.applyDriverButton?.setOnClickListener {
                delivery.carrier?.let { carrier ->
                    btnApplyClickListener?.invoke(carrier.order.id, carrier.company.id)
                }

            }
            holder.cancelButton?.setOnClickListener {
                delivery.carrier?.let { carrier ->
                    btnCancelClickListener?.invoke(carrier.order.id)
                }
            }
        } else {
            holder.cancelButton?.visibility = View.GONE
            holder.applyDriverButton?.visibility = View.GONE
        }
    }

    private fun getAllDeliveryDestinations(points: List<DeliveryPoint>?): String {
        var destinationsPoints: String = Constans.EMPTY_STRING
        points?.forEach {
            destinationsPoints += "${it.address}\n"
        }
        return destinationsPoints
    }
}