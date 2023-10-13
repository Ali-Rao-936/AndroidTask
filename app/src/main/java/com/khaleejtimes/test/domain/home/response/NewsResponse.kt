package com.khaleejtimes.test.domain.home.response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)