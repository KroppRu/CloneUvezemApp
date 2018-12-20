package com.uvezem.model

import com.google.gson.annotations.SerializedName

data class Deliveries(val totalCount: Int = 0,
                      val filter: Filter,
                      @SerializedName("Deliveries")
                      val deliveries: List<DeliveriesItem>?)