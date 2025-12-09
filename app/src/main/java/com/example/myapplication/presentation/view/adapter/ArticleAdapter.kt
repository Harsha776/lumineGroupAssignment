package com.example.myapplication.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myapplication.R
import com.example.myapplication.data.remote.ArticleDao
import com.example.myapplication.databinding.ItemArticleListBinding
import com.example.myapplication.utility.CommonUtility
import com.example.myapplication.utility.CommonUtility.dpToPx

class ArticleAdapter:RecyclerView.Adapter<ArticleAdapter.UserViewHolder>() {
    val articaleList:ArrayList<ArticleDao> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemArticleListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    fun setData(list:ArrayList<ArticleDao>?){
        list?.let {
            this.articaleList.addAll(it)
            notifyDataSetChanged()
        }

    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position,articaleList.get(position))
    }

    override fun getItemCount(): Int = articaleList.size

    inner class UserViewHolder(val binding: ItemArticleListBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(position:Int,article:ArticleDao){
                binding.tvTitle.setText(article.title)
                binding.tvShortDes.setText(article.description)
                binding.tvStartDate.setText(CommonUtility.dateFormat(article.publishedAt))
                binding.tvEndDate.setText(CommonUtility.dateFormat(article.publishedAt))
                val radius = 10.dpToPx(binding.ivImage.context)
                Glide.with(binding.ivImage.context)
                    .load(article.urlToImage)
                    .transform(RoundedCorners(radius))   // corner radius in px
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivImage)
            }
        }
}