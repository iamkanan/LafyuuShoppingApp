package com.kanan.lafyu.data.repository

import com.kanan.lafyu.data.remote.API
import com.kanan.lafyu.data.models.request.LoginRequestModel
import com.kanan.lafyu.data.models.request.RegisterRequestModel
import com.kanan.lafyu.data.models.response.LoginResponseModel
import com.kanan.lafyu.data.models.response.RegisterResponseModel
import com.kanan.lafyu.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: API
) {


    suspend fun loginRequest(email: String, password: String): Resource<LoginResponseModel> {
        val request = LoginRequestModel(email, password)
        try {
            val response = api.login(request)
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

    suspend fun registerRequest(
        name: String,
        email: String,
        password: String
    ): Resource<RegisterResponseModel> {
        val request = RegisterRequestModel(name, email, password)
        try {
            val response = api.register(request)
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