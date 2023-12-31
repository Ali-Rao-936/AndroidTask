package com.khaleejtimes.test.presentation.home.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.domain.home.usesase.HomeApiUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeApiUseCase: HomeApiUseCase) : ViewModel() {

    private val state = MutableStateFlow<HomeStateModel>(HomeStateModel.Init)
    val mState: StateFlow<HomeStateModel> get() = state
    private val TAG = "HomeViewModel"

    private fun setLoading() {
        state.value = HomeStateModel.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = HomeStateModel.IsLoading(false)
    }

    fun getHomeItems(query : String) = viewModelScope.launch {
//        if (this.isActive){
//            this.cancel()
//        }
        homeApiUseCase.execute(HomeApiUseCase.Params(query))
            .onStart {
                Log.d(TAG, " Called on start")
                setLoading()
            }
            .collect {
                hideLoading()
                Log.d(TAG, " Called collect")

                when (it) {
                    is DataState.GenericError -> {
                        Log.d(TAG, " Called Generic error")

                        state.value = HomeStateModel.StatusFailed(it.error)
                    }

                    is DataState.Success -> {
                        Log.d(TAG, "Enter SUCCESS")
                        state.value = HomeStateModel.HomeResponse(it.value)


                    }
                }

            }
    }

}