package com.kanan.lafyu.data.repository

import android.util.Log
import com.kanan.lafyu.data.models.homeResponse.HomePageResponse
import com.kanan.lafyu.data.models.offerResponse.OfferResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OfferRepository@Inject constructor(
    private val api: API
) {

    suspend fun getOfferPage(token: String, id: Int): Resource<OfferResponseModel> {
        try {
            val response = api.offer("Bearer $token",id )
            Log.d("sassasa","success offer")
            return Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error("Internet connection not found")
        } catch (e: HttpException) {
            Resource.Error("Response can not parse")
        } catch (e: UnknownHostException) {
            Resource.Error("UnknownHostException")
        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }
        return Resource.Error("Something went wrong")


    }

}