package com.example.composeapplication.banya.http

import com.example.composeapplication.banya.data.DoubanTopItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RetrofitData {

    private val client = OkHttpClient.Builder()            //builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS)      //连接超时时间
        .readTimeout(10, TimeUnit.SECONDS)         //读取超时
        .writeTimeout(10, TimeUnit.SECONDS)        //写超时，即请求超时
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.wmdb.tv/")
        .addConverterFactory(GsonConverterFactory.create()) //数据转换器
        .build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}

interface ApiServer {
    @GET(value = "api/v1/top")
    suspend fun queryUser(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query(value = "type", encoded = true) type: String
    ): List<DoubanTopItem>

//    @GET(value = "api/v1/top")
//    fun getFilm(): Call<List<Movie>>
}
