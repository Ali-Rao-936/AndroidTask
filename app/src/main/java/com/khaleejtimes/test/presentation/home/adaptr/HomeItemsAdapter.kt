package com.khaleejtimes.test.presentation.home.adaptr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaleejtimes.test.R
import com.khaleejtimes.test.databinding.HomeItemLayoutBinding
import com.khaleejtimes.test.domain.home.response.Article
import com.khaleejtimes.test.presentation.home.NewsDetailsActivity
import com.khaleejtimes.test.utils.Constants.ITEM_POSITION
import com.khaleejtimes.test.utils.Utils
import com.khaleejtimes.test.utils.loadImageFromPath

class HomeItemsAdapter(val context: Context, private val list: List<Article>) :
    RecyclerView.Adapter<HomeItemsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: HomeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val intent = Intent(context, NewsDetailsActivity::class.java)
                intent.putExtra(ITEM_POSITION, adapterPosition)
                (context as Activity).startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.article = list[position]
    }


}

