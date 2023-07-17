package com.example.newsapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Constants
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    private val TAG = this::class.java.simpleName
    private var article: Article? = null

    @Inject
    lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = this.requireArguments().getSerializable(Constants.ARTICLE_BUNDLE) as Article
        Log.d(TAG,"$article")
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArticleBinding {
        return FragmentArticleBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipOnItemSelected()
        val isWebView = pref.getBoolean(Constants.IS_WEB_VIEW, false)
        selectedChip(isWebView)
        Log.d(TAG, "selected view is web = $isWebView")
        article?.let {
            if (isWebView) {
                it.url?.let { it1 -> populateArticleDetailsWebView(it1) }
            } else {
                populateArticleDetailsNativeView(it)
            }
        }
    }

    private fun populateArticleDetailsWebView(articleUrl: String) {
        binding.nativeGroup.visibility = View.GONE
        binding.webView.apply {
            visibility = View.VISIBLE
            webViewClient = WebViewClient()
            loadUrl(articleUrl)
        }
    }

    private fun populateArticleDetailsNativeView(article: Article) {
        binding.nativeGroup.visibility = View.VISIBLE
        binding.webView.visibility = View.GONE
        binding.tvTitle.text = article.title
        binding.tvArticleDescription.text = article.description
        binding.tvArticleContent.text = article.content
        Glide.with(this).load(article.urlToImage).into(binding.detailedImage)

    }

    private fun chipOnItemSelected() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, _ ->
            when (group.checkedChipId) {
                binding.chipNative.id -> {
                    Log.d(TAG, "selected chip is false")
                    pref.edit().putBoolean(Constants.IS_WEB_VIEW, false).apply()
                    article?.let { populateArticleDetailsNativeView(it) }
                }
                binding.chipWeb.id -> {
                    Log.d(TAG, "selected chip is true")
                    pref.edit().putBoolean(Constants.IS_WEB_VIEW, true).apply()
                    article?.let { it.url?.let { it1 -> populateArticleDetailsWebView(it1) } }
                }
            }
        }
    }

    private fun selectedChip(isWeb: Boolean) {
        if (isWeb) {
            binding.chipWeb.isChecked = true
        } else {
            binding.chipNative.isChecked = true
        }

    }
}