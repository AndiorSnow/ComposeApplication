package com.example.composeapplication.banya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composeapplication.banya.ui.BanyaApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BanyaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BanyaApp()
        }

//        val apiService = RetrofitData.create(ApiServer::class.java)
//        apiService.queryUser("Imdb").enqueue(object : Callback<DoubanTop> {
//            override fun onFailure(call: Call<DoubanTop>, t: Throwable) {
//                Log.e("Retrofit", t.message?:"unknown reason")
//            }
//
//            override fun onResponse(call: Call<DoubanTop>, response: Response<DoubanTop>) {
//                Log.e("Retrofit", response.body()?.toString()?:"response is null")
//            }
//        })
        //HiOkHttp.get()
        //HiOkHttp.getAsync()
        //HiOkHttp.post()
    }
}

