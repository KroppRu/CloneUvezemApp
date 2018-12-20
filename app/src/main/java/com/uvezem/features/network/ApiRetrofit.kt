package com.uvezem.features.network

import com.uvezem.model.Deliveries
import com.uvezem.model.UserApp
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiRetrofit {

    @GET("/v2/user/login")
    @Headers("Content-Type: application/json")
    fun loginUser(@Header("Authorization") base64: String): Single<UserApp>

    @GET("v2/delivery/get-free")
    @Headers("Content-Type: application/json")
    fun loadFreeBids(@Header("Authorization") token: String): Single<Deliveries>
}