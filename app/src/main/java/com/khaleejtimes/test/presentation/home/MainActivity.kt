package com.khaleejtimes.test.presentation.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.khaleejtimes.test.databinding.ActivityMainBinding
import com.khaleejtimes.test.domain.base.ErrorResponsee
import com.khaleejtimes.test.domain.home.response.Article
import com.khaleejtimes.test.domain.home.response.NewsResponse
import com.khaleejtimes.test.presentation.home.adaptr.HomeItemsAdapter
import com.khaleejtimes.test.presentation.home.model.HomeStateModel
import com.khaleejtimes.test.presentation.home.model.HomeViewModel
import com.khaleejtimes.test.utils.Utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: ProgressDialog
    private val TAG = "MainActivity"

    lateinit var newsList: List<Article>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recyclerview orientation
        binding.rvHome.layoutManager = LinearLayoutManager(this)

        // setup view model
        initObserver()

        //network call
        viewModel.getHomeItems("tesla")

        //search
        binding.etSearch.setOnEditorActionListener(OnEditorActionListener{ search, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_GO) {

                if(search.text.toString() != ""){
                    viewModel.getHomeItems(search.text.toString())
                    hideKeyboard(binding.etSearch)
                }

                return@OnEditorActionListener true
            }
            false
        })
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

    private fun handleFailure(errorResponse: ErrorResponsee?) {
        dialog.dismiss()
        errorResponse?.let {
            Snackbar.make(binding.root, it.errorResponse?.errorMessage!!, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun handleException(exception: Exception) {
        Log.d(TAG, exception.message.toString())

    }

    private fun handleApiResponse(response: NewsResponse) {
        Log.d(TAG, response.articles.size.toString())
        binding.rvHome.adapter = HomeItemsAdapter(this, response.articles)
        dialog.dismiss()
        newsList = response.articles
    }

    private fun handleIsLoadingState(loading: Boolean) {
        if (loading) {
            Log.d(TAG, "show loader....")
            dialog = ProgressDialog.show(
                this@MainActivity, "",
                "Loading. Please wait...", true
            )
        } else {
            Log.d(TAG, "..... stop loader")
            dialog.dismiss()
        }
    }

}