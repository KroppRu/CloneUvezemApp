package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Status")
    val status: OrderStatus
)