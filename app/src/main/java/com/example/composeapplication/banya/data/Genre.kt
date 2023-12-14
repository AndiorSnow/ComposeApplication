package com.example.composeapplication.banya.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val name: String
) {
    override fun toString() = name
}