package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class DeliveriesItem(
    @SerializedName("Status") val status: DeliveryStatus,
    @SerializedName("CoordinateLoadLat") val coordinateLoadLat: Double?,
    @SerializedName("Organization") val organization: String?,
    @SerializedName("AddressWarehouse") val addressWarehouse: String?,
//  val entityName: Null = null,
//  val truckName: Null = null,
//  val driverName: Null = null,
    @SerializedName("Refrigerator") val refrigerator: Boolean = false,
    @SerializedName("DeliveryPoints") val deliveryPoints: List<DeliveryPoint>?,
    @SerializedName("TrailName") val trailName: String?,
    @SerializedName("PriceDelivery") val priceDelivery: Int = 0,
    @SerializedName("CoordinateLoadLon") val coordinateLoadLon: Double = 0.0,
    @SerializedName("Date") val date: String = "",
    @SerializedName("Weight") val weight: Int = 0,
    @SerializedName("Order") val order: Order?,
    @SerializedName("LogistPhone") val logistPhone: String?,
    @SerializedName("Number") val number: Int = 0,
    @SerializedName("Volume") val volume: Int = 0,
    @SerializedName("DateShipment") val dateShipment: String = "",
    @SerializedName("NumberDelivery") val numberDelivery: Int = 0,
    @SerializedName("Id") val id: Int = 0,
    @SerializedName("CargoType") val cargoType: String = ""
)