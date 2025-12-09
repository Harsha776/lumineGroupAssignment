package com.example.myapplication.data.remote

data class ArticlesDao(
    val articles: ArrayList<ArticleDao>?=null,
    val status: String?=null,
    val totalResults: Int?=null
)

data class ArticleDao(
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val source: SourceDao?=null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
)

data class SourceDao(
    val id: String?=null,
    val name: String?=null
)
