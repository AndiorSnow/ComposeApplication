package com.example.composeapplication.banya.http

import android.util.Log
import com.example.composeapplication.banya.data.filmList
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

object HiOkHttp {
    private val BASE_URL = "https://api.wmdb.tv"
    val client = OkHttpClient.Builder()                   //builder构造者设计模式
        .connectTimeout(10, TimeUnit.SECONDS)      //连接超时时间
        .readTimeout(10, TimeUnit.SECONDS)         //读取超时
        .writeTimeout(10, TimeUnit.SECONDS)        //写超时，即请求超时
        .build()

    //android分为主线程和子线程，
    //主线程就是APP启动之后，android framework层会启动一个线程，即主线程（UI线程）
    //子线程  --new Thread().start()

    fun get() {   //网络请求接口
        Thread(Runnable {//子线程
            //构造请求体
            val requst: Request = Request.Builder()
                .url("$BASE_URL/api/v1/top?type=Imdb&skip=0&limit=50&lang=Cn")
                .build()
            //构造请求对象
            val call: Call = client.newCall(requst)
            //发起同步请求execute--同步执行，100ms，1000ms，同步请求不能放在主线程中
            val request = call.execute()
            //请求的数据转换为String返回
            val body = request.body?.string()
            Log.e("OKHTTP"," get response: $body")
        }).start()
    }

    fun getAsync() {   //异步
        val requst: Request = Request.Builder()
            .url("$BASE_URL/api/v1/top?type=Imdb&skip=0&limit=50&lang=Cn")
            .build()
        //构造请求对象
        val call: Call = client.newCall(requst)
        //发起异步请求execute--异步执行，100ms，1000ms
        call.enqueue(object : Callback {
            //两个函数的回调都是在子线程中的，需要切换到主线程中才能操作UI控件
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", "get response onFailure:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    filmList(body)
                }
                Log.e("OKHTTP"," get response: $body")
            }
        })

    }

    //post同步请求
    fun post() {
        val body = FormBody.Builder()
            .add("userId", "1600932269")
            .add("tagID", "71")
            .build()
        val request = Request.Builder().url("$BASE_URL/tag/toggleTagFollow")
            .post(body)
            .build()
        val call = client.newCall(request)
        Thread(Runnable {
            val response = call.execute()
            Log.e("OKHTTP", "post response:${response.body?.string()}")
        }).start()
    }

}