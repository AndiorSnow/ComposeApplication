//package com.example.composeapplication.banya.data
//
//import androidx.lifecycle.LiveData
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.liveData
//
//object FilmRepository {
//
//    fun getPagingData(): LiveData<PagingData<Movie>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                initialLoadSize = 10,
//            ),
//            pagingSourceFactory = { FilmDataSource() }
//        ).liveData
//    }
//}