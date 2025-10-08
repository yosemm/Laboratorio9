package com.example.lab9.feature.wishlist.domain.model

data class Product(
    val id: Int,
    val name: String,
    val isWishlisted: Boolean = false
)
