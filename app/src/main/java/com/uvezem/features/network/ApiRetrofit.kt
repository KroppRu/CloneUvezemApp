package com.uvezem.features.network

import com.uvezem.model.UserApp
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiRetrofit {

    @Headers("Content-Type: application/json")
    @GET("/user/login")
    fun loginUser(@Header("Authorization") base64: String): Single<UserApp>


}