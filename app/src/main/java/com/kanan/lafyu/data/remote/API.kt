package com.kanan.lafyu.data.remote

import com.kanan.lafyu.data.models.authRequest.LoginRequestModel
import com.kanan.lafyu.data.models.authRequest.RegisterRequestModel
import com.kanan.lafyu.data.models.homeResponse.HomePageResponse
import com.kanan.lafyu.data.models.authResponse.LoginResponseModel
import com.kanan.lafyu.data.models.authResponse.RegisterResponseModel
import com.kanan.lafyu.data.models.categoryResponse.CategoryResponseModel
import com.kanan.lafyu.data.models.detailResponse.DetailResponseModel
import com.kanan.lafyu.data.models.notificationResponse.NotificationActivityResponseModel
import com.kanan.lafyu.data.models.notificationResponse.NotificationFeedResponseModel
import com.kanan.lafyu.data.models.notificationResponse.NotificationResponseModel
import com.kanan.lafyu.data.models.offerResponse.OfferResponseModel
import com.kanan.lafyu.data.models.reviewResponse.ReviewResponseModel
import retrofit2.http.*

interface API {

    @POST("login")
    suspend fun login(@Body body: LoginRequestModel): LoginResponseModel

    @POST("register")
    suspend fun register(@Body body: RegisterRequestModel): RegisterResponseModel

    @GET("home-page")
    suspend fun homePage(@Header("Authorization") token: String): HomePageResponse

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("key") searchText: String
    ): HomePageResponse

    @GET("product-detail/{pId}")
    suspend fun productDetail(
        @Header("Authorization") token: String,
        @Path("pId") productId: Int
    ): DetailResponseModel

    @GET("offer-screen/{id}")
    suspend fun offer(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): OfferResponseModel

    @GET("reviews/{id}")
    suspend fun review(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ReviewResponseModel


    @GET("categories")
    suspend fun listCategory(@Header("Authorization") token: String): List<CategoryResponseModel>


    @GET("notifications/offer")
    suspend fun notificationOffer(@Header("Authorization") token: String): List<NotificationResponseModel>


    @GET("notifications/feed")
    suspend fun notificationFeed(@Header("Authorization") token: String): List<NotificationFeedResponseModel>


    @GET("notifications/activity")
    suspend fun notificationActivity(@Header("Authorization") token: String): List<NotificationActivityResponseModel>


}