package com.example.composeapplication.banya

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.banya.BanyaScreen.Book
import com.example.composeapplication.banya.BanyaScreen.Film
import com.example.composeapplication.banya.BanyaScreen.Music
import com.example.composeapplication.banya.http.ApiServer
import com.example.composeapplication.banya.http.DoubanTop
import com.example.composeapplication.banya.http.RetrofitData
import com.example.composeapplication.banya.ui.FilmScreen
import com.example.composeapplication.banya.ui.components.BanyaTabRow
import com.example.composeapplication.banya.ui.theme.BanyaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BanyaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val filmList: Response

        setContent {
            BanyaApp()
        }

        //HiOkHttp.get()
        //HiOkHttp.getAsync()
        //HiOkHttp.post()

        //Retrofit的onFailure和onResponse的回调都是在主线程中
        val apiService = RetrofitData.create(ApiServer::class.java)
        apiService.queryUser("Imdb").enqueue(object : Callback<DoubanTop> {
            override fun onFailure(call: Call<DoubanTop>, t: Throwable) {
                Log.e("Retrofit", t.message?:"unknown reason")
            }

            override fun onResponse(call: Call<DoubanTop>, response: Response<DoubanTop>) {
                Log.e("Retrofit", response.body()?.toString()?:"response is null")
            }
        })
    }
}

@Composable
fun BanyaApp() {
    @OptIn(ExperimentalMaterial3Api::class)
    BanyaTheme {
        val allScreens = BanyaScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = BanyaScreen.fromRoute(        //跳转到字符串对应的页面
            backStackEntry.value?.destination?.route
        )
        Scaffold(
            topBar = {
                BanyaTabRow(
                    allScreens = allScreens,
                    //点击后进入选中的页面
                    onTabSelected = { screen ->   //枚举实例
                         navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            BanyaNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun BanyaNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Book.name,
        modifier = modifier
    ) {
        composable(
            route = Book.name
        ) {
            Text(text = "Book")
        }

        composable(
            route = Film.name
        ) {
            FilmScreen()
        }

        composable(
            route = Music.name
        ) {
            Text(text = "Music")
        }
    }
}