package com.example.newsapp.di

import android.app.Application
import android.content.Context
import com.example.newsapp.data.repository.MainRepository
import com.example.newsapp.ui.NewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideMainViewModel(repository: MainRepository): NewsViewModel {
        return NewsViewModel(repository)
    }
}
