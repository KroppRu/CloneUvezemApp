package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class OfferInfo(
    @SerializedName("Id")
    val offerId: Int,
    @SerializedName("DriverId")
    val driverId: Int,
    @SerializedName("TruckId")
    val truckId: Int,
    @SerializedName("TrailId")
    val trailId: Int
)