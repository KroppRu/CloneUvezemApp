package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Drivers(
    @SerializedName("Trail")
    val trail: Trail,
    @SerializedName("Truck")
    val truck: Truck,
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("Name")
    val name: String = ""
) {

    override fun toString(): String =
        name
}