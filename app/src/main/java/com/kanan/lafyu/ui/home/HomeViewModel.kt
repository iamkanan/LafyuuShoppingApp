package com.kanan.lafyu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.homeResponse.HomePageResponse
import com.kanan.lafyu.data.repository.HomeRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    init {
        userDataRepository.getToken()?.let {
            getHomePage(it)
        }
    }

    private val _data = MutableStateFlow<Resource<HomePageResponse>?>(null)
    val data = _data.asStateFlow()

    private fun getHomePage(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)
            val response = homeRepository.getHomePage(token)
            _data.emit(response)

        }
    }
}