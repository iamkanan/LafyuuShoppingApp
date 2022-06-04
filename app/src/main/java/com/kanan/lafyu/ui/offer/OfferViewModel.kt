package com.kanan.lafyu.ui.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.homeResponse.HomePageResponse
import com.kanan.lafyu.data.models.offerResponse.OfferResponseModel
import com.kanan.lafyu.data.repository.HomeRepository
import com.kanan.lafyu.data.repository.OfferRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel@Inject constructor(
    private val offerRepository: OfferRepository,
    private val userDataRepository: UserDataRepository
): ViewModel() {


    private val _data = MutableStateFlow<Resource<OfferResponseModel>?>(null)
    val data = _data.asStateFlow()

    fun getOfferPage(id: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)

            userDataRepository.getToken()?.let {
                val response = offerRepository.getOfferPage(it, id)
                _data.emit(response)
            }

        }
    }



}