package com.hanif.nexmedistest.data.repository.contract

import com.hanif.nexmedistest.data.db.entity.DBMovieCategory
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.DBMovieList
import com.hanif.nexmedistest.data.db.entity.relation.MovieFavoriteRelation

interface MovieLocalDSContract {

    suspend fun insertAllMovies(list: List<DBMovieList>): Boolean

    suspend fun insertAllCategory(list: List<DBMovieCategory>): Boolean

    suspend fun getPopularMovie(): List<DBMovieList>

    suspend fun discoverMovieByGenre(id: Int): List<DBMovieList>

    suspend fun searchMovies(query: String): List<DBMovieList>

    suspend fun getMovieCategory(): List<DBMovieCategory>

    suspend fun setMovieAsFavorite(movie: DBMovieFavorite): Boolean

    suspend fun removeMovieFromFavorite(movieID: Int): Boolean

    suspend fun getFavoriteMovies(): List<MovieFavoriteRelation>

}