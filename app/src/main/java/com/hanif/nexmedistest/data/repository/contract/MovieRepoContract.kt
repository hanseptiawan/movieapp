package com.hanif.nexmedistest.data.repository.contract

import com.hanif.nexmedistest.domain.model.MovieCategoryView
import com.hanif.nexmedistest.domain.model.MovieListView

interface MovieRepoContract {

    suspend fun setAppOffline(isOffline: Boolean)

    suspend fun getBaseConfiguration()

    suspend fun getPopularMovie(): List<MovieListView>

    suspend fun discoverMovieByGenre(id: Int): List<MovieListView>

    suspend fun searchMovies(query: String): List<MovieListView>

    suspend fun getMovieCategory(): List<MovieCategoryView>

    suspend fun setMovieAsFavorite(movieID: Int): Boolean

    suspend fun removeMovieFromFavorite(movieID: Int): Boolean

    suspend fun getFavoriteMovies(): List<MovieListView>

}