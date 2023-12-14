package com.example.composeapplication.banya.data

//fun filmList(jsonList: String): List<Movie> {
//    // json转换成对象
////    val json = "{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}"
//    val gson = Gson()
////    val account: Account2 = gson.fromJson<Account2>(json, Account2::class.java)
////    println(account.toString())
//    // json转换成集合
//    //val jsonList =
//    val accountList: DoubanTop =gson.fromJson(jsonList, object : TypeToken<DoubanTop>(){}.type)
//    val movies: MutableList<Movie> = mutableListOf()
//
//    for (i in accountList) {
//        val movie = Movie(
//            "${i.doubanId}",
//            "${i.data[0].name}",
//            "${i.doubanRating}",
//            "${i.data[0].genre}",
//            "${i.data[0].poster}"
//        )
//        movies.add(movie)
//    }
//    return movies.toList()
//    println("fromJson to List: ${accountList.size}")
//    //println(accountList[0].castRatings)
//}

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

//class DoubanTop : ArrayList<DoubanTopItem>()

data class DoubanTopItem(
    val alias: String,
    val castRatings: Int,
    val createdAt: Long,
    val data: List<Film>,
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

data class MovieItem(
    val doubanId: String?,
    val name: String?,
    val doubanRating: String?,
    val genre: String?,
    val poster: String?
)

fun DoubanTopItem.asEntry() : MovieItem {
    return MovieItem(
        doubanId = this.doubanId,
        name = this.data[0].name,
        doubanRating = this.doubanRating,
        genre = this.data[0].genre,
        poster = this.data[0].poster,
    )
}