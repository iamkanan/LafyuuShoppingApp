package com.kanan.lafyu.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanan.lafyu.data.models.categoryResponse.CategoryResponseModel
import com.kanan.lafyu.data.repository.CategoryRepository
import com.kanan.lafyu.data.repository.UserDataRepository
import com.kanan.lafyu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel@Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userDataRepository: UserDataRepository
): ViewModel() {

    private val _data = MutableStateFlow<Resource<List<CategoryResponseModel>>?>(null)
    val data = _data.asStateFlow()

    init {
        userDataRepository.getToken()?.let {
            getCategoryPage(it)
        }
    }

    private fun getCategoryPage(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(Resource.Loading)
            val response = categoryRepository.getCategoryPage(token)
            _data.emit(response)
        }


    }

}