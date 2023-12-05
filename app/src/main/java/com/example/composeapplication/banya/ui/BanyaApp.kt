package com.example.composeapplication.banya.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.banya.ui.components.BanyaTabRow
import com.example.composeapplication.banya.ui.compose.FilmScreen
import com.example.composeapplication.banya.ui.theme.BanyaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BanyaApp() {
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
        startDestination = BanyaScreen.Book.name,
        modifier = modifier
    ) {
        composable(
            route = BanyaScreen.Book.name
        ) {
            Text(
                text = "Book",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        composable(
            route = BanyaScreen.Film.name
        ) {
            FilmScreen(  )
        }

        composable(
            route = BanyaScreen.Music.name
        ) {
            Text(
                text = "Music",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}