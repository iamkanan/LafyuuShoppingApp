package com.kanan.lafyu.data.repository

import android.util.Log
import com.kanan.lafyu.data.models.detailResponse.DetailResponseModel
import com.kanan.lafyu.data.models.offerResponse.OfferResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class DetailRepository@Inject constructor(
    private val api: API
) {


    suspend fun getDetailPage(token: String, id: Int): Resource<DetailResponseModel> {
        try {
            val response = api.productDetail("Bearer $token", id)
            Log.d("sassasa","success detail")
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