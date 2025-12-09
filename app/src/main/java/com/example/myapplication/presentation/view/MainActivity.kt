package com.example.myapplication.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.view.fragments.ArticleListFragment
import com.example.myapplication.utility.CommonUtility

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        CommonUtility.switchFragment( supportFragmentManager,ArticleListFragment())
    }
}