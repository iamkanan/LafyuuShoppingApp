package com.kanan.lafyu.data.models.offerResponse

data class OfferResponseModel(
    val image: String ?= null,
    val products: List<Product> ?= null,
    val title: String ?= null
)