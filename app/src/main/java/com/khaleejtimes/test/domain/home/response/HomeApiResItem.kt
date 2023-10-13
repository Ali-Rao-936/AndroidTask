package com.khaleejtimes.test.domain.home.response

data class HomeApiResItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)