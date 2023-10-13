package com.khaleejtimes.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaleejtimes.test.databinding.ActivityMainBinding
import com.khaleejtimes.test.domain.home.response.NewsResponse
import com.khaleejtimes.test.presentation.home.HomeStateModel
import com.khaleejtimes.test.presentation.home.HomeViewModel
import com.khaleejtimes.test.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : HomeViewModel

    private lateinit var binding : ActivityMainBinding
    private val TAG = "MainActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHome.layoutManager = LinearLayoutManager(this)
        initObserver()
        viewModel.getHomeItems("tesla")

    }
    private fun initObserver() {
        viewModel.mState.flowWithLifecycle(
            this.lifecycle, Lifecycle.State.STARTED
        ).onEach {
            handleState(it)
        }.launchIn(this.lifecycleScope)
    }



    private fun handleState(state: HomeStateModel) {
        when (state) {
            is HomeStateModel.IsLoading -> handleIsLoadingState(state.isLoading)
            is HomeStateModel.HomeResponse -> handleApiResponse(state.homeApiResponse)
            is HomeStateModel.FoundException -> handleException(state.exception)
            is HomeStateModel.StatusFailed -> handleFailure(state.message)
            else -> {
            }
        }
    }

    private fun handleFailure(message: String) {
        showToast(message)
    }

    private fun handleException(exception: Exception) {
        Log.d(TAG, exception.message.toString())

    }

    private fun handleApiResponse(response: NewsResponse) {
        Log.d(TAG, response.articles.size.toString())
        showToast(response.articles.size.toString())
     // binding.rvHome.adapter =  HomeItemsAdapter(this, response)
    }

    private fun handleIsLoadingState(loading: Boolean) {
        if (loading)
            Log.d(TAG, "show loader....")
        else
            Log.d(TAG, "..... stop loader")
    }

}