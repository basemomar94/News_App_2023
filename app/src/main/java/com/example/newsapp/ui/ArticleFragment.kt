package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.utils.Constants

class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    private var article: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = this.requireArguments().getString(Constants.ARTICLE_URL_BUNDLE)
    }
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArticleBinding {
        return FragmentArticleBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article?.let {
            populateArticleDetails(it)
        }
    }

    private fun populateArticleDetails(articleUrl: String) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(articleUrl)
        }
    }
}