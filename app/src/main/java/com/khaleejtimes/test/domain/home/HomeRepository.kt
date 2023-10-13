package com.khaleejtimes.test.domain.home

import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.domain.home.response.NewsResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

  suspend fun homeResponse(query : String) : Flow<DataState<NewsResponse>>

}