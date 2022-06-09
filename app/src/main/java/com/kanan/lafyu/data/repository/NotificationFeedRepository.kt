package com.kanan.lafyu.data.repository

import com.kanan.lafyu.data.models.notificationResponse.NotificationFeedResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotificationFeedRepository@Inject constructor(
    private val api:API
) {

    suspend fun getNotificationFeedPage(token: String): Resource<List<NotificationFeedResponseModel>> {
        try {
            val response = api.notificationFeed("Bearer $token")
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