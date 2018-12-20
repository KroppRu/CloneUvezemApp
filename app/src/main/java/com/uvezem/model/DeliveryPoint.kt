package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class DeliveryPoint(
    @SerializedName("Address") val address: String = "",
    @SerializedName("Store") val store: String = "",
    @SerializedName("Lon") val lon: Double = 0.0,
    @SerializedName("City") val city: String = "",
    @SerializedName("SubjectRF") val subjectRF: String = "",
    @SerializedName("DateDelivery") val dateDelivery: String = "",
    @SerializedName("Lat") val lat: Double = 0.0
)