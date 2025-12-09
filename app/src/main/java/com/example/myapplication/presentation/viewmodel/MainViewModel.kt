package com.example.myapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.ArticlesDao
import com.example.myapplication.data.repository.PostRepositoryImpl
import com.example.myapplication.data.remote.RetrofitInstance
import com.example.myapplication.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _posts = MutableLiveData<ArticlesDao>()
    val posts: LiveData<ArticlesDao> = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error



    val repository = PostRepositoryImpl(RetrofitInstance.api)
    val useCase = GetPostsUseCase(repository)

    fun fetchPosts() {
        val coroutineHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        }
        viewModelScope.launch(Dispatchers.IO +coroutineHandler) {
            try {
                useCase.getPost().collect{ postList->
                    Log.d("TAG", "fetchPosts: "+postList.articles?.size)
                    _posts.postValue(postList)
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}