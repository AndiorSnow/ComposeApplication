package com.example.composeapplication.banya.http

import android.util.Log
import com.example.composeapplication.banya.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//private val client = OkHttpClient.Builder()            //builder构造者设计模式
//    .connectTimeout(10, TimeUnit.SECONDS)      //连接超时时间
//    .readTimeout(10, TimeUnit.SECONDS)         //读取超时
//    .writeTimeout(10, TimeUnit.SECONDS)        //写超时，即请求超时
////        .apply {
////            if (BuildConfig.DEBUG) {
////                addInterceptor(HttpLoggingInterceptor {
////                    Log.d("net", it)
////                }.setLevel(HttpLoggingInterceptor.Level.BODY))
////            }
////        }
//    .build()
//
//private var retrofit: Retrofit = Retrofit.Builder()
//    .client(client)
//    .baseUrl("https://api.wmdb.tv/")
//    .addConverterFactory(GsonConverterFactory.create()) //数据转换器
//    .build()
//
////    fun <T> create(clazz: Class<T>): T {
////        return retrofit.create(clazz)
////    }
//
//

//
//
//object RetrofitData : ApiServer by retrofit.create(ApiServer::class.java)

@InstallIn(SingletonComponent::class)
@Module
object RetrofitData {

    private val TAG: String = RetrofitData.javaClass.simpleName

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            Log.d(TAG, it)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.wmdb.tv")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        //Log.d("MovieDataSource", "load")
        return retrofit.create(MovieService::class.java)
    }
}