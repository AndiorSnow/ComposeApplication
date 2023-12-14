package com.example.composeapplication.banya.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.composeapplication.banya.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private var api: MovieService,
    var appDatabase: AppDatabase
) {

    private val db = AppDatabase.instance
    private val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = PAGE_SIZE,
        // 开启占位符
        enablePlaceholders = true,
        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = PAGE_SIZE,
        // 初始化加载数量，默认为 pageSize * 3
        initialLoadSize = PAGE_SIZE
    )

    @OptIn(ExperimentalPagingApi::class)
    fun getPagingData2(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = RepoMediator(api, db!!)
        ) {
            Log.d("MovieDataSource", "load")
            db.movieDao().getMovie()
        }.flow.map { pagingData ->
            pagingData.map { RepoEntity2RepoMapper().map (it) }
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}

class RepoEntity2RepoMapper : Mapper<Movie, MovieItem> {
    override fun map(input: Movie): MovieItem = MovieItem(
        doubanId = input.doubanId,
        name = input.name,
        doubanRating = input.doubanRating,
        genre = input.genre,
        poster = input.poster
    )
}

interface Mapper<I, O> {
    fun map(input: I): O
}
