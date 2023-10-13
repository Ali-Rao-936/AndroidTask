package com.khaleejtimes.test.data.test

import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.data.common.utils.WrappedResponse
import com.khaleejtimes.test.data.test.remote.TestApi
import com.khaleejtimes.test.domain.test.entity.TestApiResponse
import com.khaleejtimes.test.domain.test.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImp @Inject constructor(private val testApi: TestApi): TestRepository {
    override suspend fun testResponse(): Flow<DataState<WrappedResponse<TestApiResponse>>> = flow {
        emit(DataState.Success(testApi.getTestApiResponse("entries")))
    }

    override suspend fun xyz(): String {
        return "eetete"
    }
}