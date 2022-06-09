package com.kanan.lafyu.data.repository

import com.kanan.lafyu.data.models.categoryResponse.CategoryResponseModel
import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val api: API
) {

    suspend fun getCategoryPage(token: String): Resource<List<CategoryResponseModel>> {
        try {
            val response = api.listCategory("Bearer $token")
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