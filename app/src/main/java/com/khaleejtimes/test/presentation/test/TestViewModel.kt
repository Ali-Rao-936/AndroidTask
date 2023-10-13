package com.khaleejtimes.test.presentation.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaleejtimes.test.data.common.utils.DataState
import com.google.gson.Gson
import com.khaleejtimes.test.domain.test.usecase.TestApiUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestViewModel @Inject constructor(private val testApiUseCase: TestApiUseCase) : ViewModel() {

    private val state = MutableStateFlow<TestFragmentStateModel>(TestFragmentStateModel.Init)
    val mState:StateFlow<TestFragmentStateModel> get() =state
    private val TAG = "TestViewModel"
    private fun setLoading(){

        state.value = TestFragmentStateModel.IsLoading(true)
    }
    private fun hideLoading(){

        state.value = TestFragmentStateModel.IsLoading(false)
    }

    fun getTestApiResponse() = viewModelScope.launch {

            testApiUseCase.buildUseCase(TestApiUseCase.Params())
                .onStart {
                Log.i(TAG,"Called onStart")
                setLoading()
            }
                .collect {
                hideLoading()
                Log.i(TAG,"Called Collect")
                when(it){
                    is DataState.GenericError -> {
                        Log.i(TAG,"Called Generic Error")
                    }
                    is DataState.Success -> {
                        Log.i(TAG,"Called Success")
                        it.value.data?.let{
                            Log.i(TAG,Gson().toJson(it.count))
                        }
                    }
                }
            }




    }

}
