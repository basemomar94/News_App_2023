package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), NewsAdapter.NewsInterface {
    private val TAG = this::class.java.simpleName
    private lateinit var viewModel: NewsViewModel

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        getArticles()
        collectArticles()


    }

    private fun getArticles() = lifecycleScope.launch {
        viewModel.getArticlesResponse(Constants.BBC_NEWS_SOURCE)
    }

    private fun collectArticles() = lifecycleScope.launch {
        viewModel.articlesResponse.collect { newsResponse ->
            newsResponse?.let {
                //   val bbcArticles = it.articles.filter { it.source.name == Constants.BBC_SOURCE }
                val articles = newsResponse.articles
                populateRv(articles)
              //  Log.d(TAG, "articles are ${it?.articles?.size} bbc are ${bbcArticles.size}")
            }
        }
    }

    private fun populateRv(articlesArrayList: List<Article>) {
        binding?.rvArticles?.adapter = NewsAdapter(articlesArrayList, this)
        binding?.rvArticles?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvArticles?.setHasFixedSize(true)
    }

    override fun openDetails(article: Article, position: Int) {
      
    }
}