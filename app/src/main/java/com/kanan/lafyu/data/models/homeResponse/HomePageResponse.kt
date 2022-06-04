package com.kanan.lafyu.data.models.homeResponse

import com.kanan.lafyu.data.models.homeResponse.Advertisment
import com.kanan.lafyu.data.models.homeResponse.Category
import com.kanan.lafyu.data.models.homeResponse.Item

data class HomePageResponse(
    val advertisments: List<Advertisment>? = null,
    val categories: List<Category>? = null,
    val items: List<Item>? = null
)