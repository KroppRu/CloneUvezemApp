package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Carrier(
    @SerializedName("Order")
    val order: Order,
    @SerializedName("Company")
    val company: Company
)