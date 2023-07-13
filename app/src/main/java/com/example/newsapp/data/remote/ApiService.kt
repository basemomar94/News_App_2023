package com.example.newsapp.data.remote

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("v2/top-headlines")
    suspend fun getArticleResponse(
        @Query("sources") sources: String? = null,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): NewsResponse

}