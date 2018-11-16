package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Deliveries(

    @SerializedName("Deliveries")
    val deliveries: List<Delivery> = listOf()
)