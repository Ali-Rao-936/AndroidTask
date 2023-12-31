package com.khaleejtimes.test.presentation.home.model

import com.khaleejtimes.test.domain.base.ErrorResponsee
import com.khaleejtimes.test.domain.home.response.NewsResponse
import java.lang.Exception

sealed class HomeStateModel{

    object Init : HomeStateModel()
    data class IsLoading(val isLoading : Boolean) : HomeStateModel()
    data class HomeResponse(val homeApiResponse: NewsResponse) : HomeStateModel()
    data class FoundException(val exception: Exception) : HomeStateModel()
    data class StatusFailed(val message: ErrorResponsee?) : HomeStateModel()
}
