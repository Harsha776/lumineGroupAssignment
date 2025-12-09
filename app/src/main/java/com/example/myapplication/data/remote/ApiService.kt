package com.example.myapplication.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("from") fromDate: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ):ArticlesDao
}