package com.kanan.lafyu.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.notificationResponse.NotificationResponseModel
import com.kanan.lafyu.data.repository.NotificationOfferRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationOfferViewModel @Inject constructor(
    private val notificationOfferRepository: NotificationOfferRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {


    private val _data = MutableStateFlow<Resource<List<NotificationResponseModel>>?>(null)
    val data = _data.asStateFlow()

    init {
        userDataRepository.getToken()?.let {
            getNotificationOfferPage(it)
        }
    }


    private fun getNotificationOfferPage(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)
            val response = notificationOfferRepository.getNotificationOfferPage(token)
            _data.emit(response)
        }
    }

}