package com.kanan.lafyu.data.models.reviewResponse

data class Review(
    val comment: String?= null,
    val date: String?= null,
    val fullName: String?= null,
    val photo: String?= null,
    val productId: Int?= null,
    val rating: Double?= null,
    val reviewId: Int?= null
)