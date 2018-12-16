package com.uvezem.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserApp(
    @SerializedName("Type")
    val type: String,
    @SerializedName("Token")
    val token: String,
    @SerializedName("Login")
    val login: String,
    @SerializedName("Name")
    val name: String
) : Serializable