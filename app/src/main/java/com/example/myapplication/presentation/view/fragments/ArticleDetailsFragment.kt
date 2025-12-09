package com.example.myapplication.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentArticleDetailsBinding

import com.example.myapplication.presentation.viewmodel.MainViewModel
import com.example.myapplication.utility.CommonUtility

class ArticleDetailsFragment : Fragment() {
    lateinit var  viewModel : MainViewModel
    lateinit var binidng: FragmentArticleDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binidng = FragmentArticleDetailsBinding.inflate(layoutInflater,container, false)
        return binidng.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.articleDetails.observe(requireActivity()) { article ->
            Log.d("TAG", "onViewCreated: "+article.urlToImage)
            article?.let {
                Glide.with(binidng.headerImage.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binidng.headerImage)
                binidng.tvContent.setText(article.content)
                binidng.tvDate.setText("Digitimes  ${CommonUtility.dateFormat(article.publishedAt)}")
            }


        }
        binidng.ivBack.setOnClickListener {
           CommonUtility.popFragment(requireActivity().supportFragmentManager)
        }


    }
}