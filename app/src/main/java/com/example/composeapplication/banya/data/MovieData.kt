package com.example.composeapplication.banya.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val doubanId: String,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "doubanRating") val doubanRating: String,
    @ColumnInfo(name = "genre")  val genre: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "page") val page: Int
)