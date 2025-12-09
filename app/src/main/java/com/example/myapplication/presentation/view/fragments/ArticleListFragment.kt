package com.example.myapplication.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentArticleListBinding
import com.example.myapplication.presentation.view.adapter.ArticleAdapter
import com.example.myapplication.presentation.viewmodel.MainViewModel

class ArticleListFragment : Fragment() {
    lateinit var  viewModel : MainViewModel
    lateinit var binidng: FragmentArticleListBinding
    var pageCount=1
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
        binidng.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binidng.recyclerView.adapter = adapter
        binidng.progressBar.visibility=View.VISIBLE
        viewModel.fetchPosts(pageCount)

        binidng.tvLoadMore.setOnClickListener {
            pageCount+=1
            viewModel.fetchPosts(pageCount)
        }


        viewModel.posts.observe(requireActivity()) { posts ->
            binidng.progressBar.visibility=View.GONE
            binidng.recyclerView.visibility=View.VISIBLE
            binidng.loadMoreView.visibility = View.GONE
            if (posts.articles.isNullOrEmpty()){
                Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }else{
                adapter.setData(posts.articles)
            }


        }

        viewModel.error.observe(requireActivity()){
            binidng.progressBar.visibility=View.GONE
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()

        }

        binidng.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val totalItems = layoutManager.itemCount
                if (lastVisible == totalItems - 1 && pageCount<5) {
                   binidng.loadMoreView.visibility = View.VISIBLE
                    binidng.tvTotal.setText(getString(R.string.total_results)+" ${adapter.articaleList.size}")
                }else{
                    binidng.loadMoreView.visibility = View.GONE
                }
            }
        })

    }
}