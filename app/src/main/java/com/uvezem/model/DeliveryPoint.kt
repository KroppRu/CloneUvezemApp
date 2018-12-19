package com.uvezem.model

data class DeliveryPoint(val address: String = "",
                         val store: String = "",
                         val lon: Double = 0.0,
                         val city: String = "",
                         val subjectRF: String = "",
                         val dateDelivery: String = "",
                         val lat: Double = 0.0)