package com.khaleejtimes.test.data.test.module

import com.khaleejtimes.test.data.test.TestRepositoryImp
import com.khaleejtimes.test.data.test.remote.TestApi
import com.khaleejtimes.test.domain.test.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
object TestApiModule{

    @Provides
    fun providesTestRepository(testApi: TestApi): TestRepository {
        return TestRepositoryImp(testApi)
    }

}


