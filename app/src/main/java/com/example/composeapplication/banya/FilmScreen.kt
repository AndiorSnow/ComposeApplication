package com.example.composeapplication.banya

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.composeapplication.banya.data.Movie

@Composable
fun FilmScreen(
    viewModel: MovieViewModel = viewModel()) {
    //val itemList = (0..5).toList()
    //实现分页效果
    val data = viewModel.fetchLastedMovies().collectAsLazyPagingItems()

    var backgroundColor by remember {
        mutableStateOf(Color.LightGray)
    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
        columns = GridCells.Fixed(3),
    ) {
        items(
            count = data.itemCount,
            key = { index ->
                val item = data[index]
                "${ item?.doubanId ?: ""}${index}"
            }
        ) {index ->
            //Text(text = "Item is $index")
            val item = data[index] ?: return@items
            MovieCard(movie = item!!)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(movie: Movie) {
    Card (
        shape = RectangleShape,
        colors = CardDefaults.cardColors(Color.LightGray),
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .padding(bottom = 10.dp)
            .padding(top = 10.dp)
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = movie.poster,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight,
                alpha = DefaultAlpha,
                colorFilter = null,
                requestBuilderTransform = { it },
                loading = placeholder {
                    val modifier = Modifier
                    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(Modifier
                            .size(40.dp)
                            .padding(top = 90.dp)
                        )
                    }
                }
            )
            Text(text = movie.name ?: "")
            Text(text = "评分：${movie.doubanRating ?: ""}")
        }
    }
}

