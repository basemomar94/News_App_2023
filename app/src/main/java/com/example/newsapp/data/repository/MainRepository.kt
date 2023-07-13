package com.example.newsapp.data.repository

import com.example.newsapp.models.NewsResponse


interface MainRepository {

   suspend fun getArticles(source:String): NewsResponse

}