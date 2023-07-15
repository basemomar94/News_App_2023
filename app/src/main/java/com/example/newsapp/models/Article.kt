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
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Article -> {
                (other.source == this.source && other.author == this.author && other.content == this.content && other.description == this.description && other.publishedAt == this.publishedAt && other.title == this.title && other.url == this.url && this.urlToImage == this.urlToImage)
            }
            else -> false
        }
    }

}