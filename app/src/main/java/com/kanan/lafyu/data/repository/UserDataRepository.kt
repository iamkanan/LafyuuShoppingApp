package com.kanan.lafyu.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.kanan.lafyu.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {


    fun getToken(): String? {
        val sharedPref = context.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE)
        return sharedPref?.getString(Constants.TOKEN, null)
    }

    fun setToken(token: String?) {
        val sharedPref: SharedPreferences? =
            context.getSharedPreferences(Constants.USER, Context.MODE_PRIVATE)
        val sharedPrefEditor: SharedPreferences.Editor? = sharedPref?.edit()
        sharedPrefEditor?.putString(Constants.TOKEN, token)
        sharedPrefEditor?.apply()
    }
}