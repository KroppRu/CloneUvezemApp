package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Company(@SerializedName("Persons")
                   val persons: List<Person>?,
                   @SerializedName("Id")
                   val id: Int = 0,
                   @SerializedName("Name")
                   val name: String = "")