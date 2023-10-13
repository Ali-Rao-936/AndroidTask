package com.khaleejtimes.test.domain.home.usesase


import com.khaleejtimes.test.domain.home.HomeRepository
import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.data.common.utils.NetworkHelper
import com.khaleejtimes.test.domain.home.response.NewsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeApiUseCase @Inject constructor(private val homeRepository: HomeRepository) : NetworkHelper<HomeApiUseCase.Params, NewsResponse>() {

     data class Params(val query: String)

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<NewsResponse>> {
      return  homeRepository.homeResponse(parameter.query)
    }

}