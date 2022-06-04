package com.kanan.lafyu.ui.review

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.reviewResponse.ReviewResponseModel
import com.kanan.lafyu.data.repository.ReviewRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _data = MutableStateFlow<Resource<ReviewResponseModel>?>(null)
    val data = _data.asStateFlow()


    fun getReviewPage(id: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)

            userDataRepository.getToken()?.let {
                val response = reviewRepository.getReviewPage(it, id)
                _data.emit(response)
            }

        }
    }


}