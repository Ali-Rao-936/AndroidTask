package com.khaleejtimes.test.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaleejtimes.test.R
import com.khaleejtimes.test.databinding.HomeItemLayoutBinding
import com.khaleejtimes.test.domain.home.response.HomeApiRes
import com.khaleejtimes.test.utils.loadImageFromPath

class HomeItemsAdapter(val context: Context, private val list: HomeApiRes) : RecyclerView.Adapter<HomeItemsAdapter.viewHolder>() {



   inner class viewHolder(val binding : HomeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding =  HomeItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.imgItem.loadImageFromPath(list[position].image, R.drawable.placeholder)
        holder.binding.txtProductName.text = list[position].title
        holder.binding.txtPrice.text = "AED " + list[position].price
    }
}