package com.kanan.lafyu.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.notificationResponse.NotificationActivityResponseModel
import com.kanan.lafyu.data.repository.NotificationActivityRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationActivityViewModel @Inject constructor(
    private val notificationActivityRepository: NotificationActivityRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _data = MutableStateFlow<Resource<List<NotificationActivityResponseModel>>?>(null)
    var data = _data.asStateFlow()

    init {
        userDataRepository.getToken()?.let {
            getNotificationActivityPage(it)
        }
    }


    private fun getNotificationActivityPage(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)
            val response = notificationActivityRepository.getNotificationActivityPage(token)
            _data.emit(response)
        }


    }

}