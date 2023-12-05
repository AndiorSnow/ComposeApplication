package com.example.composeapplication.banya.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.composeapplication.banya.ui.Dimens.InactiveTabOpacity
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDelay
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDuration
import com.example.composeapplication.banya.ui.Dimens.TabFadeOutAnimationDuration
import com.example.composeapplication.banya.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(
    viewModel: MovieViewModel = viewModel(),
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    //实现分页效果
    val data = viewModel.fetchLastedMovies().collectAsLazyPagingItems()

    Scaffold (
        modifier = Modifier,
        topBar = {
            GenreTabBar(
//                onGeneralClick = { viewModel.updateData() }
//                onCrimeClick = { viewModel.updateData() }
//                onComedyClick = { viewModel.updateData() }
//                onRomanceClick = { viewModel.updateData() }
//                onScifiClick = { viewModel.updateData() }
            )
        }
    ) { contentPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(contentPadding),
            columns = GridCells.Fixed(3),
        ) {
            items(
                count = data.itemCount,
                key = { index ->
                    val item = data[index]
                    "${ item?.doubanId ?: ""}${index}"
                }
            ) {index ->
                val item = data[index] ?: return@items
                MovieCard(
                    movie = item!!,
                    color = backgroundColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenreTabBar(
//    onGeneralClick: () -> Unit,
//    onCrimeClick: () -> Unit,
//    onComedyClick: () -> Unit,
//    onRomanceClick: () -> Unit,
//    onScifiClick: () -> Unit,
//    modifier: Modifier
) {
    val color = MaterialTheme.colorScheme.secondary
    var state = remember { mutableStateOf(0) }
    val titles = listOf("综合", "剧情", "犯罪", "喜剧", "爱情", "科幻", "动画", "战争", "灾难", "动作")
    Column (
        //Modifier.background(MaterialTheme.colorScheme.primary),
    ){
        ScrollableTabRow(
            selectedTabIndex = state.value,
            modifier = Modifier.wrapContentWidth(),
            edgePadding = 16.dp,
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colorScheme.surface,
                    height = 4.dp,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[state.value])
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                val selected = state.value == index
                val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
                val animSpec = remember {
                    tween<Color>(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                        delayMillis = TabFadeInAnimationDelay
                    )
                }
                val tabTintColor by animateColorAsState(
                    targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
                    animationSpec = animSpec,
                    label = "",
                )
                Tab(
                    text = { Text(title, color = tabTintColor) },
                    selected = selected,
                    onClick = { state.value = index },
                    selectedContentColor = MaterialTheme.colorScheme.surface,
                    unselectedContentColor = MaterialTheme.colorScheme.primary,

//                  onClick = { viewModel.updateData() }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    color: Color
    ) {
    Card (
        shape = RectangleShape,
        colors = CardDefaults.cardColors(color),
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
                    Box(modifier = modifier.fillMaxWidth())
                }
            )
            Text(
                text = movie.name ?: "",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "评分：${movie.doubanRating ?: ""}",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}