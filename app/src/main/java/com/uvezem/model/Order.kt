package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("DeliveryId")
    val deliveryId: Int,
    @SerializedName("PartnerId")
    val partnerId: Int,
    @SerializedName("PartnerPersonId")
    val personId: Int,
    @SerializedName("Price")
    val Price: Int,
    @SerializedName("DateShipment")
    val dateShipment: String
)