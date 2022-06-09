package com.kanan.lafyu.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.notificationResponse.NotificationFeedResponseModel
import com.kanan.lafyu.data.repository.NotificationFeedRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationFeedViewModel @Inject constructor(
    private val notificationFeedRepository: NotificationFeedRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _data = MutableStateFlow<Resource<List<NotificationFeedResponseModel>>?>(null)
    var data = _data.asStateFlow()

    init {
        userDataRepository.getToken()?.let {
            getNotificationFeedPage(it)
        }
    }


    private fun getNotificationFeedPage(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)
            val response = notificationFeedRepository.getNotificationFeedPage(token)
            _data.emit(response)
        }


    }

}