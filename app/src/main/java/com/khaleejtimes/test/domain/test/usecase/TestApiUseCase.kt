package com.khaleejtimes.test.domain.test.usecase

import com.khaleejtimes.test.data.common.utils.DataState
import com.khaleejtimes.test.data.common.utils.NetworkHelper
import com.khaleejtimes.test.data.common.utils.WrappedResponse
import com.khaleejtimes.test.domain.test.TestRepository
import com.khaleejtimes.test.domain.test.entity.TestApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestApiUseCase @Inject constructor(private val testRepository: TestRepository): NetworkHelper<TestApiUseCase.Params, WrappedResponse<TestApiResponse>>() {
    class Params

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<WrappedResponse<TestApiResponse>>> {
        return testRepository.testResponse()
    }
}