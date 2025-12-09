package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.ArticlesDao
import kotlinx.coroutines.flow.Flow

interface Repo {
    suspend fun getPost(pageCount:Int): Flow<ArticlesDao>
}