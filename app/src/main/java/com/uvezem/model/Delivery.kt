package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Delivery(

    @SerializedName("Id")
    private var id: String = EMPTY_STRING,

    @SerializedName("Status")
    var status: String = EMPTY_STRING,

    @SerializedName("OfferId")
    var offerId: String = EMPTY_STRING,

    @SerializedName("Number")
    var number: String = EMPTY_STRING,

    @SerializedName("Date")
    var date: String = EMPTY_STRING,

    @SerializedName("Organization")
    var organization: String = EMPTY_STRING,

    @SerializedName("Weight")
    var weight: Int = 0,

    @SerializedName("Volume")
    var volume: Int = 0,

    @SerializedName("CargoType")
    var cargoType: String = EMPTY_STRING,

    @SerializedName("Refrigerator")
    var refrigerator: Boolean = false,

    @SerializedName("PriceDelivery")
    var priceDelivery: Int = 0,

    @SerializedName("LogistPhone")
    var logistPhone: String = EMPTY_STRING ,

    @SerializedName("DateShipment")
    var dateShipment: Int = 0,

    @SerializedName("AddressWarehouse")
    var addressWarehouse: String = EMPTY_STRING,

    var deliveryPlace: String = EMPTY_STRING,

    @SerializedName("CoordinateLoadLat")
    var coordinateLoadLat: Double = 0.0,

    @SerializedName("CoordinateLoadLon")
    var coordinateLoadLon: Double = 0.0,

    @SerializedName("NumberDelivery")
    var numberDelivery: String = EMPTY_STRING
//    @SerializedName("DeliveryPoints")
//
//    var deliveryPoints: List<DeliveryPoint>? = null
) {
    companion object {
        private const val EMPTY_STRING = ""
    }
}

