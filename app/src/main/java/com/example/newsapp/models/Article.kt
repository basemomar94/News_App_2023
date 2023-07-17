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

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (publishedAt?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (urlToImage?.hashCode() ?: 0)
        return result
    }

}