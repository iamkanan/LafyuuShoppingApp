package com.kanan.lafyu.data.repository

import com.kanan.lafyu.data.models.notificationResponse.NotificationActivityResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationActivityRepository@Inject constructor(
    private val api: API
){

    suspend fun getNotificationActivityPage(token: String): Resource<List<NotificationActivityResponseModel>> {
        try {
            val response = api.notificationActivity("Bearer $token")
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