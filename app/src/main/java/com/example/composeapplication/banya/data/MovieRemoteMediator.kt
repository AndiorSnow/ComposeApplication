package com.example.composeapplication.banya.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.composeapplication.banya.service.MovieService

@OptIn(ExperimentalPagingApi::class)
class RepoMediator(
    val api: MovieService,
    val db: AppDatabase
) : RemoteMediator<Int, Movie>() {

    //private val movieRemoteDataSource = MovieRemoteDataSource()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        val repoDao = db.movieDao()
        val pageKey = when (loadType) {
            //首次访问 或者调用 PagingDataAdapter.refresh()时
            LoadType.REFRESH -> null
            //在当前加载的数据集的开头加载数据时
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
            //下拉加载更多时
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                lastItem.page//返回当前页
            }
        }

        //无网络从本地数据库获取数据
//        if (!AppHelper.mContext.isConnectedNetwork()) {
//            return MediatorResult.Success(endOfPaginationReached = false)
//        }

        //请求网络分页数据
        val page = pageKey ?: 0
        val pageSize = MovieRepository.PAGE_SIZE
        val result = api.queryUser(page * pageSize, pageSize)
        Log.d("MovieDataSource", result[0].doubanId)
//        val movies =
//            movieRemoteDataSource.fetchLatestMovies(page, pageSize).map { it.asEntry() }
//        val nextKey = if (movies.isNotEmpty() && page <= 150) page + pageSize else null
//        val prevKey = if (page > pageSize) page - pageSize else null
//        PagingSource.LoadResult.Page(movies, prevKey, nextKey)
        val endOfPaginationReached = result.isEmpty()
        val items = result.map {
            Movie(
                doubanId = it.id,
                name = it.data[0].name,
                doubanRating = it.doubanRating,
                genre = it.data[0].genre,
                poster = it.data[0].poster,
                page = page + 1
            )
        }

        //插入数据库
        db.withTransaction {
            if (loadType == LoadType.REFRESH) {
                repoDao.clear()
            }
            repoDao.insert(items)
        }
        return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    }
}

//class MovieRemoteDataSource {
//    private val apiService = RetrofitData.provideMovieService()
//    private val ioDispatcher = Dispatchers.IO
//
//    suspend fun fetchLatestMovies(skip: Int, limit: Int): List<DoubanTopItem> =
//        withContext(ioDispatcher) {
//            //Log.d("MovieDataSource", "${skip}, $limit")
//            MovieService.queryUser(skip, limit)
//        }
//}