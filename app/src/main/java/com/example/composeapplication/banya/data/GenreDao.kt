package com.example.composeapplication.banya.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres ORDER BY name")
    fun getGenres(): Flow<List<Genre>>

    @Query("SELECT * FROM genres WHERE id = :id")
    fun getGenre(id: String): Flow<Genre>

    @Upsert  //如果已存在则更新，否则插入
    suspend fun upsertAll(genres: List<Genre>)
}