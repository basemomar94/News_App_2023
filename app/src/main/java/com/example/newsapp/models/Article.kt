package com.example.newsapp.models

import java.io.Serializable

data class Article(
    val source: Source,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
) : Serializable