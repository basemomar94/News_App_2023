package com.example.newsapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), NewsAdapter.NewsInterface {
    private val TAG = this::class.java.simpleName
    private var viewModel: NewsViewModel? = null
    private var newsAdapter: NewsAdapter? = null

    @Inject
    lateinit var pref: SharedPreferences

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "on view created")
        initRv()
        getArticles()
        collectArticles()

    }

    private fun getArticles() = lifecycleScope.launch {
        val source = pref.getString(Constants.NEWS_SOURCE, Constants.BBC_NEWS_SOURCE)
        binding.tvTitle.text = source
        Log.d(TAG, "source is $source")
        viewModel?.getArticlesResponse(source ?: Constants.BBC_NEWS_SOURCE)
    }

    private fun collectArticles() = lifecycleScope.launch {
        viewModel?.articlesResponse?.collect { newsResponse ->
            newsResponse.let {
                //   val bbcArticles = it.articles.filter { it.source.name == Constants.BBC_SOURCE }
                val articles = newsResponse?.articles
                if (articles != null) {
                    newsAdapter?.addList(articles)
                    binding.progressBar.visibility = View.GONE
                    Log.d(TAG, "articles size ${articles.size}")

                }
                //  Log.d(TAG, "articles are ${it.articles.size} bbc are ${bbcArticles.size}")
            }
        }
    }

    private fun initRv() {
        Log.d(TAG, "init rv")
        newsAdapter = NewsAdapter(arrayListOf(), this)
        binding.rvArticles.adapter = newsAdapter
        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.setHasFixedSize(true)
    }

    override fun openDetails(article: Article, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.ARTICLE_BUNDLE, article)
        Log.d(TAG, "bundle is $article")
        findNavController().navigate(R.id.action_homeFragment_to_articleFragment, bundle)

    }
}