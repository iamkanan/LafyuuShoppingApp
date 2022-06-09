package com.kanan.lafyu.data.models.offerResponse

data class Product(
    val discountPercent: Int ?= null,
    val discountPrice: Double ?= null,
    val images: List<String> ?= null,
    val price: Double ?= null,
    val productId: Int ?= null,
    val rating: Double ?= null,
    val specification: String ?= null,
    val thumbnailImage: String ?= null,
    val title: String ?= null
)