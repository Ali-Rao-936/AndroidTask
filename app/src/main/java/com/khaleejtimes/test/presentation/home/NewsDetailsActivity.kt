package com.khaleejtimes.test.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khaleejtimes.test.databinding.ActivityNewsDetailsBinding
import com.khaleejtimes.test.domain.home.response.Article
import com.khaleejtimes.test.utils.Constants.SELECTED_ITEM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {


    lateinit var binding: ActivityNewsDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get selected item
        val dataType = object : TypeToken<Article>() {}.type
        val article : Article = Gson().fromJson(intent.extras?.getString(SELECTED_ITEM), dataType)

        // setting xml
        binding.article = article

        // back button click
        binding.imgBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

    }

}
