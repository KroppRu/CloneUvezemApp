package com.uvezem

import android.app.Application
import com.uvezem.features.network.ApiRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
        lateinit var apiRetrofit: ApiRetrofit
            private set
    }

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("http://api.vital.softwer.ru/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    override fun onCreate() {
        super.onCreate()
        instance = this
        apiRetrofit = retrofit.create(ApiRetrofit::class.java)
    }
}