package com.example.newsapp.di

import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.data.repository.MainRepository
import com.example.newsapp.data.repository.MainRepositoryImplement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

     @Provides
    fun provideRepo(apiService: ApiService): MainRepository = MainRepositoryImplement(apiService)
}