package com.example.newsapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.models.Article

class NewsAdapter(
    var articleArrayList: List<Article>,
    val newsInterface: NewsInterface,
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    //  private val dateTimeFormat = DateTimeUtil.getUserDateFormat()


    class ViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = articleArrayList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleArrayList[position]
        holder.binding.tvArticleTitle.text = article.title
        holder.binding.tvDate.text = article.publishedAt
        holder.binding.tvSource.text = article.source.name
        holder.binding.tvArticleDescription.text = article.description
        Glide.with(holder.binding.root.context).load(article.urlToImage)
            .into(holder.binding.ivArticleImage)
        holder.binding.root.setOnClickListener {
            newsInterface.openDetails(article, position)
        }
    }


    interface NewsInterface {
        fun openDetails(article: Article, position: Int)
    }
}