package com.uvezem.model

import com.google.gson.annotations.SerializedName

enum class OrderStatus {
    @SerializedName("new_order")
    NEED_APPROVE,
    @SerializedName("approved")
    APPROVED
}