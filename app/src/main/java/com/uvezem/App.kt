package com.uvezem

import android.app.Application
import com.uvezem.data.network.ApiRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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


    override fun onCreate() {
        super.onCreate()
        instance = this

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl("http://api.vital.softwer.ru")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        apiRetrofit = retrofit.create(ApiRetrofit::class.java)
    }
}