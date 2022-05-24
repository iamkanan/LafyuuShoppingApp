package com.kanan.lafyu.data.remote

import com.kanan.lafyu.data.models.request.LoginRequestModel
import com.kanan.lafyu.data.models.request.RegisterRequestModel
import com.kanan.lafyu.data.models.response.HomePageResponse
import com.kanan.lafyu.data.models.response.LoginResponseModel
import com.kanan.lafyu.data.models.response.RegisterResponseModel
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
    suspend fun productDeatil(
        @Header("Authorization") token: String,
        @Path("pId")productId: Int
    ): HomePageResponse
}