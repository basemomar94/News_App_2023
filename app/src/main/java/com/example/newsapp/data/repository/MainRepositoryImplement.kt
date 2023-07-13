package com.example.newsapp.data.repository

import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.models.NewsResponse

class MainRepositoryImplement(private val apiService: ApiService) :
    MainRepository {
    override suspend fun getArticles(source: String): NewsResponse = apiService.getArticleResponse(sources = source)


}