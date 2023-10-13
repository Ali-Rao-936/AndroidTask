package com.khaleejtimes.test.data.home


import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.data.home.remote.HomeApi
import com.khaleejtimes.test.domain.home.HomeRepository
import com.khaleejtimes.test.domain.home.response.NewsResponse
import com.khaleejtimes.test.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val homeApi: HomeApi) : HomeRepository {
    override suspend fun homeResponse(query: String): Flow<DataState<NewsResponse>> = flow {
        emit(DataState.Success(homeApi.getHomeItemsResponse("everything",query, "2023-10-12","publishedAt", Constants.API_KEY)))
    }

}