package com.hanif.nexmedistest.presentation.ui.movielist.viewmodel

import androidx.lifecycle.LiveData
import com.hanif.nexmedistest.domain.base.BaseLiveDataState
import com.hanif.nexmedistest.domain.model.MovieCategoryView
import com.hanif.nexmedistest.domain.model.MovieListView

interface MovieListVMContract {

    fun getPopularMovie()

    fun discoverMovieByGenre(id: Int)

    fun searchMovies(query: String)

    fun observeMovies(): LiveData<BaseLiveDataState<List<MovieListView>>>

    fun getMovieCategory()

    fun observeMovieCategory(): LiveData<BaseLiveDataState<List<MovieCategoryView>>>

    fun setMovieAsFavorite(movieID: Int)

    fun observeSetMovieAsFavorite(): LiveData<BaseLiveDataState<Boolean>>

}