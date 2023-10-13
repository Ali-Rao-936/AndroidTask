package com.khaleejtimes.test.presentation.test

import java.lang.Exception

sealed class TestFragmentStateModel{
    object Init : TestFragmentStateModel()
    data class IsLoading(val isLoading:Boolean): TestFragmentStateModel()
    data class FoundException(val exception:Exception): TestFragmentStateModel()
}
