package com.example.composeapplication.banya

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composeapplication.R
import com.example.composeapplication.banya.data.Movie

@Composable
fun FilmScreen(viewModel: MovieViewModel = viewModel()) {
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
            items = data,
            key = { it.doubanId!! }
        ) {item ->
            //Text(text = "Item is $it")
            //val movieCard = data[item] ?: return@items
            MovieCard(movie = item!!)
        }
    }
}


inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline span: (LazyGridItemSpanScope.(item: T?) -> GridItemSpan)? = null,
    noinline contentType: (item: T?) -> Any? = { null },
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key == null) null else { index ->
            val item = items.peek(index)
            if (item == null) {
                MyPagingPlaceholderKey(index)
            } else {
                key(item)
            }
        },
        span = if (span != null) { { span(items[it]) } } else null,
        contentType = { index: Int -> contentType(items[index]) }
    ) { index ->
        itemContent(items[index])
    }
}

@SuppressLint("BanParcelableUsage")
data class MyPagingPlaceholderKey(private val index: Int) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<MyPagingPlaceholderKey> =
            object : Parcelable.Creator<MyPagingPlaceholderKey> {
                override fun createFromParcel(parcel: Parcel) =
                    MyPagingPlaceholderKey(parcel.readInt())

                override fun newArray(size: Int) = arrayOfNulls<MyPagingPlaceholderKey?>(size)
            }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Column (
        modifier = Modifier
            .padding(start = 8.dp, top = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_jetpack),
            contentDescription = null,
            modifier = Modifier.size(15.dp)
        )
        Text(text = movie.name ?: "")
        Text(text = movie.doubanRating ?: "")
    }
}