package com.kanan.lafyu.data.repository

import android.util.Log
import com.kanan.lafyu.data.models.reviewResponse.ReviewResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class ReviewRepository@Inject constructor(
    private val api:API
) {

    suspend fun getReviewPage(token: String, id:Int): Resource<ReviewResponseModel> {
        try {
            val response = api.review("Bearer $token", id)
            Log.d("sassasa","success review")
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