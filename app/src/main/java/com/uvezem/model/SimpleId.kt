package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class SimpleId(
    @SerializedName("Id")
    val id: Int
)