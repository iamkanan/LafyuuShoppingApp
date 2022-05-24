package com.kanan.lafyu.data.models.response

data class HomePageResponse(
    val advertisments: List<Advertisment>? = null,
    val categories: List<Category>? = null,
    val items: List<Item>? = null
)