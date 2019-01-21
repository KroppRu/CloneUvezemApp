package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Trail(
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("Name")
    val name: String = ""
) {

    override fun toString(): String =
        name
}