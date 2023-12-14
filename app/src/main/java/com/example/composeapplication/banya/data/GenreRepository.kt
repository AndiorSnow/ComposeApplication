package com.example.composeapplication.banya.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    private val genreDao: GenreDao
) {

    fun getGenres() = genreDao.getGenres()

    fun getGenre(id: String) = genreDao.getGenre(id)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GenreRepository? = null

        fun getInstance(genreDao: GenreDao) =
            instance ?: synchronized(this) {
                instance ?: GenreRepository(genreDao).also { instance = it }
            }
    }
}