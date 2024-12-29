package com.hanif.nexmedistest.presentation.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import com.hanif.nexmedistest.domain.base.BaseLiveDataState
import com.hanif.nexmedistest.domain.model.MovieListView

interface FavoriteVMContract {

    suspend fun setMovieAsFavorite(movieID: Int)

    suspend fun observeSetMovieAsFavorite(): LiveData<BaseLiveDataState<Boolean>>

    suspend fun removeMovieFromFavorite(movieID: Int)

    suspend fun observeRemoveMovieFromFavorite(): LiveData<BaseLiveDataState<Boolean>>

    suspend fun getFavoriteMovies()

    suspend fun observeGetFavoriteMovies(): LiveData<BaseLiveDataState<List<MovieListView>>>

}