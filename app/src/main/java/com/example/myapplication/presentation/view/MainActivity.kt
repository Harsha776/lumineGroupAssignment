package com.example.myapplication.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.presentation.viewmodel.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var  viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.fetchPosts()

        viewModel.posts.observe(this) { posts ->
            posts.articles?.forEach {
                Log.d("TAG", "onCreate: "+it.title)
            }

            // Update RecyclerView/Compose UI
        }

        viewModel.error.observe(this){

        }
    }
}