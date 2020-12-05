package com.jcodonuts.app.data.remote.model

import com.jcodonuts.app.R
import com.jcodonuts.app.ui.base.BaseModel

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : BaseModel {
    override fun layoutId(): Int {
        return R.layout.item_article
    }
}