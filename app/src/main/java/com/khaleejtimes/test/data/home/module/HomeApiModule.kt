package com.khaleejtimes.test.data.home.module

import com.khaleejtimes.test.domain.home.HomeRepository
import com.khaleejtimes.test.data.home.HomeRepoImp
import com.khaleejtimes.test.data.home.remote.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent :: class)
object HomeApiModule{

    @Provides
    fun providesHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepoImp(homeApi)
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

}