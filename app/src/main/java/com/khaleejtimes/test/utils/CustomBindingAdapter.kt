package com.khaleejtimes.test.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.khaleejtimes.test.R

@BindingAdapter ("imageFromUrl")
fun ImageView.imageFromUrl(url:String) {
    if (url.isNotEmpty()) {
        Glide
            .with(this.context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(this)

    }
}