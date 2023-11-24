package com.example.composeapplication.banya.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun filmList(jsonList: String): List<Movie> {
    // json转换成对象
//    val json = "{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}"
    val gson = Gson()
//    val account: Account2 = gson.fromJson<Account2>(json, Account2::class.java)
//    println(account.toString())
    // json转换成集合
    //val jsonList =
    val accountList: DoubanTop =gson.fromJson(jsonList, object : TypeToken<DoubanTop>(){}.type)
    val movies: MutableList<Movie>

    accountList.forEachIndexed { i, item ->
        var movie = Movie(accountList[i].doubanId, accountList[i].doubanId)
        movie.doubanId = accountList[i].doubanId,
        movie.name = accountList[0].filmInfo[0].name,
        movie.doubanRating = accountList[0].doubanRating,
        movie.genre = accountList[0].filmInfo[0].genre,
        movie.poster = accountList[0].filmInfo[0].poster
        movies.add(movie)
    }

    return movies
    Movie(
        doubanId = accountList[0].doubanId,
        name = accountList[0].filmInfo[0].name,
        doubanRating = accountList[0].doubanRating,
        genre = accountList[0].filmInfo[0].genre,
        poster = accountList[0].filmInfo[0].poster
    )
    println("fromJson to List: ${accountList.size}")
    //println(accountList[0].castRatings)
}

//data class Account2 (
//    var uid: String,
//    var userName:String,
//    var password:String,
//    var telNumber:String
//)
//
//class Account {
//    var uid: String = ""
//    var userName:String="Freeman"
//    var password:String="password"
//    var telNumber:String="13000000000"
//    override fun toString(): String {
//        return "Account(uid='$uid', userName='$userName', password='$password', telNumber='$telNumber')"
//    }
//}

class DoubanTop : ArrayList<DoubanTopItem>()

data class DoubanTopItem(
    val alias: String,
    val castRatings: Int,
    val createdAt: Long,
    val filmInfo: List<Film>,
    val dateReleased: String,
    val doubanId: String,
    val doubanRating: String,
    val doubanVotes: Int,
    val duration: Int,
    val episodes: Int,
    val id: String,
    val imdbId: String,
    val imdbRating: String,
    val imdbVotes: Int,
    val musicRatings: Int,
    val originalName: String,
    val plotRatings: Int,
    val rottenRating: String,
    val rottenVotes: Int,
    val totalSeasons: Int,
    val totalVotes: Int,
    val type: String,
    val updatedAt: Long,
    val visionRatings: Int,
    val year: String
)

data class Film(
    val country: String,
    val createdAt: Long,
    val description: String,
    val genre: String,
    val id: String,
    val lang: String,
    val language: String,
    val movie: String,
    val name: String,
    val poster: String,
    val shareImage: String,
    val updatedAt: Long
)

data class Movie(
    val doubanId: String,
    val name: String,
    val doubanRating: String,
    val genre: String,
    val poster: String
)