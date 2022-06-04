package com.kanan.lafyu.data.models.offerResponse

data class OfferResponseModel(
    val image: String,
    val products: List<Product>,
    val title: String
)