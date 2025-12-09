package com.example.myapplication.domain.usecase

import com.example.myapplication.data.remote.ArticlesDao
import com.example.myapplication.domain.repository.Repo
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(val repo: Repo) : Repo by repo{
    suspend fun invoke(pageCount:Int): Flow<ArticlesDao> {
        return repo.getPost(pageCount)
    }
}