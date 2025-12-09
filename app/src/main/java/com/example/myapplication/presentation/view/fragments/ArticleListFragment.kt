package com.example.myapplication.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentArticleListBinding
import com.example.myapplication.presentation.view.adapter.ArticleAdapter
import com.example.myapplication.presentation.viewmodel.MainViewModel

class ArticleListFragment : Fragment() {
    lateinit var  viewModel : MainViewModel
    lateinit var binidng: FragmentArticleListBinding
    val adapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binidng = FragmentArticleListBinding.inflate(layoutInflater,container, false)
        return binidng.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        binidng.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binidng.recyclerView.adapter = adapter
        binidng.progressBar.visibility=View.VISIBLE
        viewModel.fetchPosts()


        viewModel.posts.observe(requireActivity()) { posts ->
            binidng.progressBar.visibility=View.GONE
            binidng.recyclerView.visibility=View.VISIBLE
            adapter.setData(posts.articles)

        }

        viewModel.error.observe(requireActivity()){
            binidng.progressBar.visibility=View.GONE
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()

        }
    }
}