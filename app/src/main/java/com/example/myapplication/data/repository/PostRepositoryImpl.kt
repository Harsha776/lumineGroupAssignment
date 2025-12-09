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

class PostRepositoryImpl(val api:ApiService):Repo {
    override suspend fun getPost() : Flow<ArticlesDao> = flow {
            emit(api.getArticles("tesla","2025-11-09","publishedAt",1,20,"2a854e4230354358b1fae3909d8cb0b3")/*.map {
                Log.d("TAG", "getPost: "+it.id)
                it.toDoMain() }*//*.filter { it.title=="qui est esse" }*/)
        }.flowOn(Dispatchers.IO).catch { e ->
            // handle error
            emit(ArticlesDao())
        }
    }