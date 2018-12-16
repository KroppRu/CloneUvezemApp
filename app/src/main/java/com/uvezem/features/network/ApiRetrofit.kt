package com.uvezem.features.network

import com.uvezem.model.UserApp
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit

interface ApiRetrofit {

    @Headers("Content-Type: application/json")
    @GET("/user/login")
    fun loginUser(): Single<UserApp>


}