package com.kanan.lafyu.data.models.detailResponse

data class Info(
    val discountPercent: Int,
    val discountPrice: Double,
    val images: List<String>,
    val price: Double,
    val productId: Int,
    val rating: Double,
    val specification: String,
    val thumbnailImage: String,
    val title: String
)