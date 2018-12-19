package com.uvezem.model

data class Deliveries(val totalCount: Int = 0,
                      val filter: Filter,
                      val deliveries: List<DeliveriesItem>?)