package com.example.composeapplication.banya.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeapplication.banya.ui.compose.FilmScreen

//import com.example.composeapplication.banya.data.UserData
//import com.example.composeapplication.banya.ui.books.BooksBody
//import com.example.composeapplication.banya.ui.films.FilmsBody
//import com.example.composeapplication.banya.ui.musics.MusicsBody

/**
 * Screen metadata for Banya.
 */
enum class BanyaScreen(
    val icon: ImageVector,
    val body: @Composable ((String) -> Unit) -> Unit
) {
    Book(
        icon = Icons.Filled.Book,
        body = {
            //BooksBody()
        }
    ),
    Film(
        icon = Icons.Filled.PlayCircleFilled,
        body = {
            FilmScreen()
        }
    ),
    Music(
        icon = Icons.Filled.MusicNote,
        body = {
            //MusicsBody(UserData.bills)
        }
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): BanyaScreen =
            when (route?.substringBefore("/")) {
                Book.name -> Book
                Film.name -> Film
                Music.name -> Music
                null -> Film
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
