package com.khaleejtimes.test.data.test.remote

import com.khaleejtimes.test.data.common.utils.WrappedResponse
import com.khaleejtimes.test.domain.test.entity.TestApiResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface TestApi {
    @GET
    suspend fun getTestApiResponse(@Url url:String): WrappedResponse<TestApiResponse>
}