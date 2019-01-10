package com.uvezem.model

import com.google.gson.annotations.SerializedName

enum class DeliveryStatus(val alias: String) {

    @SerializedName("free_delivery")
    FREE("free_delivery"),

    @SerializedName("filling_data")
    FILLING("filling_data"),

    @SerializedName("complete_delivery")
    COMPLETE("complete_delivery")
}