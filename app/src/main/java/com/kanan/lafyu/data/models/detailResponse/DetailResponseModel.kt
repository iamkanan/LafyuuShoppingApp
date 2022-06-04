package com.kanan.lafyu.data.models.detailResponse

data class DetailResponseModel(
    val info: Info? = null,
    val sizes: List<String>? = null,
    val specification: String? = null,
    val topReview: TopReview? = null
)