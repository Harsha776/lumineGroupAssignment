package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.remote.ArticlesDao
import com.example.myapplication.domain.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRepositoryImpl(
    private val api: ApiService
) : Repo {
    
    override suspend fun getPost(pageCount: Int): Flow<ArticlesDao> = flow {
        emit(
            api.getArticles(
                query = DEFAULT_QUERY,
                fromDate = DATE,
                sortBy = DEFAULT_SORT_BY,
                page = pageCount,
                pageSize = DEFAULT_PAGE_SIZE,
                apiKey = API_KEY
            )
        )
    }.flowOn(Dispatchers.IO).catch { e ->
        Log.e(TAG, "Error fetching posts: ${e.message}", e)
        emit(ArticlesDao())
    }
    
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
    
    companion object {
        private const val TAG = "PostRepositoryImpl"
        private const val DEFAULT_QUERY = "tesla"
        private const val DEFAULT_SORT_BY = "publishedAt"
        private const val DEFAULT_PAGE_SIZE = 20
        private const val DATE = "2025-11-09"
        private const val API_KEY = "2a854e4230354358b1fae3909d8cb0b3"
    }
}