package com.example.composeapplication.banya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composeapplication.banya.data.FilmRepository

class MovieViewModel : ViewModel() {
    fun fetchLastedMovies() = FilmRepository.getPagingData().cachedIn(viewModelScope)
}