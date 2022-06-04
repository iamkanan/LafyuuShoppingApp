package com.kanan.lafyu.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.detailResponse.DetailResponseModel
import com.kanan.lafyu.data.repository.DetailRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {


    private val _data = MutableStateFlow<Resource<DetailResponseModel>?>(null)
    val data = _data.asStateFlow()


    fun getDetailPage(id: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)

            userDataRepository.getToken()?.let {
                val response = detailRepository.getDetailPage(it, id)
                _data.emit(response)
            }

        }
    }

}