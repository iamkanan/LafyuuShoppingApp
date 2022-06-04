package com.kanan.lafyu.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.authResponse.LoginResponseModel
import com.kanan.lafyu.data.models.authResponse.RegisterResponseModel
import com.kanan.lafyu.data.repository.AuthRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _data = MutableSharedFlow<Resource<LoginResponseModel>>()
    val data = _data.asSharedFlow()

    private val _dataRegister = MutableSharedFlow<Resource<RegisterResponseModel>>()
    val dataRegister = _dataRegister.asSharedFlow()


    fun login (email:String? , password: String?) {
        viewModelScope.launch {
            _data.emit(Resource.Loading)
            if(email.isNullOrEmpty() && password.isNullOrEmpty()) {
                _data.emit(Resource.Error("Fields can not empty!"))
                return@launch
            }
            val response = authRepository.loginRequest(email ?: "",password?: "")
            _data.emit(response)

        }

    }

    fun register (name:String? ,email:String?, password: String?) {
        viewModelScope.launch {
            _dataRegister.emit(Resource.Loading)
            if(name.isNullOrEmpty() && email.isNullOrEmpty() && password.isNullOrEmpty()) {
                _dataRegister.emit(Resource.Error("Fields can not empty!"))
                return@launch
            }
            val response = authRepository.registerRequest(name ?: "", email ?: "", password ?: "")
            _dataRegister.emit(response)

        }

    }





}