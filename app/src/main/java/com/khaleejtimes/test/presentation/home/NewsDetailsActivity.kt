package com.khaleejtimes.test.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khaleejtimes.test.databinding.ActivityNewsDetailsBinding
import com.khaleejtimes.test.presentation.home.MainActivity.Companion.newsList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {


    lateinit var binding: ActivityNewsDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get selected position
        val position = intent.extras?.getInt("itemPosition")

        // setting xml
        binding.article = newsList[position ?: 0]

        // back button click
        binding.imgBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

    }

}
