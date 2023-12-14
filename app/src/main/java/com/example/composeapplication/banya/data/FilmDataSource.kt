//package com.example.composeapplication.banya.data
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.composeapplication.banya.http.ApiServer
//import com.example.composeapplication.banya.http.RetrofitData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class FilmDataSource : PagingSource<Int, Movie>() {
//    private val filmRemoteDataSource = FilmRemoteDataSource()
//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        Log.d("MovieDataSource", "load")
//        return try {
//            val page = params.key ?: 0
//            val pageSize = params.loadSize
//            val movies =
//                filmRemoteDataSource.fetchLatestMovies(page, pageSize).map { it.asEntry() }
//            val nextKey = if (movies.isNotEmpty() && page <= 150) page + pageSize else null
//            val prevKey = if (page > pageSize) page - pageSize else null
//            Log.d("MovieDataSource", "next: $nextKey")
//            LoadResult.Page(movies, prevKey, nextKey)
//        } catch (e: Exception) {
//            throw e
//            LoadResult.Error(e)
//        }
//    }
//}
//
//class FilmRemoteDataSource {
//    private val apiService = ApiServer.RetrofitData
//    private val ioDispatcher = Dispatchers.IO
//
//    suspend fun fetchLatestMovies(skip: Int, limit: Int): List<DoubanTopItem> =
//        withContext(ioDispatcher) {
//            Log.d("MovieDataSource", "${skip}, $limit")
//            apiService.queryUser(skip, limit)
//        }
//}