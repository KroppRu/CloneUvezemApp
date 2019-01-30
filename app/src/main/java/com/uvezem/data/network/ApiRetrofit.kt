package com.uvezem.data.network

import com.uvezem.model.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface ApiRetrofit {

    @GET("/v2/user/login")
    @Headers("Content-Type: application/json")
    fun loginUser(@Header("Authorization") base64: String): Single<UserApp>

    @GET("v2/delivery/get-free")
    @Headers("Content-Type: application/json")
    fun loadFreeBids(@Header("Authorization") token: String): Single<Deliveries>

    @GET("v2/delivery/get-active")
    @Headers("Content-Type: application/json")
    fun loadMyBids(@Header("Authorization") token: String): Single<Deliveries>

    @GET("v2/delivery/{bidId}")
    @Headers("Content-Type: application/json")
    fun loadFreeBid(@Header("Authorization") token: String, @Path("bidId") bidId: Int): Single<DeliveriesItem>

    @GET("v2/user/get-companies")
    @Headers("Content-Type: application/json")
    fun loadCompanyData(@Header("Authorization") token: String): Single<List<Company>>

    @POST("v2/order-delivery/create")
    @Headers("Content-Type: application/json")
    fun createOffer(@Header("Authorization") token: String, @Body offer: Offer): Single<Order>

    @GET("v2/user/get-company/{companyId}")
    @Headers("Content-Type: application/json")
    fun loadCompanyDetails(@Header("Authorization") token: String, @Path("companyId") companyId: Int): Single<CompanyDetail>

    @POST("v2/order-delivery/attach-driver")
    @Headers("Content-Type: application/json")
    fun attachInfo(@Header("Authorization") token: String, @Body offerInfo: OfferInfo): Completable

    @POST("v2/order-delivery/refuse")
    @Headers("Content-Type: application/json")
    fun cancelOrder(@Header("Authorization") token: String, @Body simpleId: SimpleId): Completable

    @GET("v2/delivery/get-completed")
    @Headers("Content-Type: application/json")
    fun loadArchiveBids(@Header("Authorization") token: String): Single<Deliveries>

}