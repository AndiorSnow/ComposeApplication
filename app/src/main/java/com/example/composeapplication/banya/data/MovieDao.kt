package com.example.composeapplication.banya.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getMovie(): PagingSource<Int, Movie>

    @Query("SELECT * FROM Movie WHERE genre = :genreName")
    fun getMovieByGenre(genreName: String): PagingSource<Int, Movie>

    @Query("DELETE FROM Movie")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieList: List<Movie>)

    @Delete
    fun delete(repo: Movie)

    @Update
    fun update(repo: Movie)
}
