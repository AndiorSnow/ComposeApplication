package com.example.composeapplication.banya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composeapplication.banya.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject internal constructor (
//    application: Application,
    private var movieRepository: MovieRepository,
//    private var genreRepository: GenreRepository,
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun postOfData() = movieRepository.getPagingData2().cachedIn(viewModelScope)


//    private val genres = genreRepository.getGenres().asLiveData()
//

    //fun fetchLastedMovies() = FilmRepository.getPagingData().cachedIn(viewModelScope)
//
//    private val filmGenre: MutableStateFlow<Int> = MutableStateFlow(
//        savedStateHandle["GENRE_SAVED_STATE_KEY"] ?: 0
//    )
//
//    val films: LiveData<PagingData<Movie>> = filmGenre.flatMapLatest { genre ->
//        FilmRepository.getPagingData(genre)
//    }.asLiveData()
//
//
//    init {
//        viewModelScope.launch {
//            filmGenre.collect { newGenre ->
//                savedStateHandle["GENRE_SAVED_STATE_KEY"] = newGenre
//            }
//        }
//    }
//
//    fun updateData(genre: Int) {
//        if (genre == 0) {
//
//        } else {
//            val genre = genres.value?.get(genre)
//        }
//
//        val data = fetchLastedMovies()
//        data
//        if (genre)
//    }
}