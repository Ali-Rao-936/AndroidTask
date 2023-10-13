package com.khaleejtimes.test.data.home.remote

import com.khaleejtimes.test.domain.home.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface HomeApi {
    @GET
    suspend fun getHomeItemsResponse(@Url url : String, @Query("q") search : String,@Query("from") date : String,@Query("sortBy") sortBy : String,
                                     @Query("apiKey") apiKey : String ) : NewsResponse
}