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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.composeapplication.R
import com.example.composeapplication.banya.data.MovieItem
import com.example.composeapplication.banya.ui.Dimens.InactiveTabOpacity
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDelay
import com.example.composeapplication.banya.ui.Dimens.TabFadeInAnimationDuration
import com.example.composeapplication.banya.ui.Dimens.TabFadeOutAnimationDuration
import com.example.composeapplication.banya.viewmodel.FilmViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(
    viewModel: FilmViewModel = hiltViewModel(),
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    //实现分页效果
    val data = viewModel.postOfData().collectAsLazyPagingItems()
    //val data = viewModel.fetchLastedMovies().collectAsLazyPagingItems()
    val titles : Array<String> = stringArrayResource(id = R.array.film_genre)

    Scaffold (
        modifier = Modifier,
        topBar = {
            GenreTabBar(
                viewModel = viewModel,
                titles = titles
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
    viewModel: FilmViewModel = hiltViewModel(),
    titles: Array<String>
//    onGeneralClick: () -> Unit,
//    onCrimeClick: () -> Unit,
//    onComedyClick: () -> Unit,
//    onRomanceClick: () -> Unit,
//    onScifiClick: () -> Unit,
//    modifier: Modifier
) {
    val colorSelected = MaterialTheme.colorScheme.surface
    val colorUnselected = MaterialTheme.colorScheme.secondary
    var state = remember { mutableIntStateOf(0) }
    val titles : Array<String> = titles
    Column {
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
                    targetValue = if (selected) colorSelected else colorUnselected.copy(alpha = InactiveTabOpacity),
                    animationSpec = animSpec,
                    label = "",
                )
                Tab(
                    text = { Text(title, color = tabTintColor) },
                    selected = selected,

                    onClick = {
                        //Log.d("MovieDataSource", title)
                        state.value = index
                        //viewModel.updateData(index)
                        },
                    selectedContentColor = MaterialTheme.colorScheme.surface,
                    unselectedContentColor = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
    movie: MovieItem,
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