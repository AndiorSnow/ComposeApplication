package com.example.composeapplication.banya.service

import com.example.composeapplication.banya.data.DoubanTopItem
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET(value = "api/v1/top?type=Douban")

    suspend fun queryUser(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("lang") lang: String = "Cn"
    ): List<DoubanTopItem>
}
