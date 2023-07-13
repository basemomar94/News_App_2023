package com.example.newsapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.MainRepository
import com.example.newsapp.models.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val TAG = this::class.java.simpleName
    private val _articlesResponse: MutableStateFlow<NewsResponse?> = MutableStateFlow(null)
    val articlesResponse = _articlesResponse

    fun getArticlesResponse(source: String) = viewModelScope.launch {
        try {
            Log.d(TAG, "got articles")
            _articlesResponse.emit(repository.getArticles(source))
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }
}