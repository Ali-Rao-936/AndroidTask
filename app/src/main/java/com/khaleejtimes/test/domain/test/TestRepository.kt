package com.khaleejtimes.test.domain.test

import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.data.common.utils.WrappedResponse
import com.khaleejtimes.test.domain.test.entity.TestApiResponse
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun testResponse(): Flow<DataState<WrappedResponse<TestApiResponse>>>
    suspend fun xyz():String
}