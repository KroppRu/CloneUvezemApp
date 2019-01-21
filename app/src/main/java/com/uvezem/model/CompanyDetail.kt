package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class CompanyDetail(
    @SerializedName("Drivers")
    val drivers: List<Drivers>?,
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("Name")
    val name: String = ""
)