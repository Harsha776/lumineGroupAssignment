package com.example.myapplication.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentArticleDetailsBinding

import com.example.myapplication.presentation.viewmodel.MainViewModel
import com.example.myapplication.utility.CommonUtility

class ArticleDetailsFragment : Fragment() {
    private lateinit var  viewModel : MainViewModel
    private lateinit var binidng: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binidng = FragmentArticleDetailsBinding.inflate(layoutInflater,container, false)
        return binidng.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.articleDetails.observe(requireActivity()) { article ->
            article?.let {
                Glide.with(binidng.headerImage.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binidng.headerImage)
                binidng.tvContent.text = article.content
                binidng.tvDate.text = "Digitimes  ${CommonUtility.dateFormat(article.publishedAt)}"
            }


        }
        binidng.ivBack.setOnClickListener {
           CommonUtility.popFragment(requireActivity().supportFragmentManager)
        }


    }
}