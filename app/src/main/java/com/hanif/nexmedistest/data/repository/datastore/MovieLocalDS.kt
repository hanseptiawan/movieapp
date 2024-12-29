package com.hanif.nexmedistest.data.repository.datastore

import com.hanif.nexmedistest.data.db.dao.DAOMovieCategory
import com.hanif.nexmedistest.data.db.dao.DAOMovieFavorite
import com.hanif.nexmedistest.data.db.dao.DAOMovieList
import com.hanif.nexmedistest.data.db.entity.DBMovieCategory
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.DBMovieList
import com.hanif.nexmedistest.data.db.entity.relation.MovieFavoriteRelation
import com.hanif.nexmedistest.data.repository.contract.MovieLocalDSContract
import javax.inject.Inject

class MovieLocalDS @Inject constructor(
    private val daoMovieList: DAOMovieList,
    private val daoMovieCategory: DAOMovieCategory,
    private val daoMovieFavorite: DAOMovieFavorite
): MovieLocalDSContract {

    override suspend fun insertAllMovies(list: List<DBMovieList>): Boolean {
        try {
            daoMovieList.insertAll(list)
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertAllCategory(list: List<DBMovieCategory>): Boolean {
        try {
            daoMovieCategory.insertAll(list)
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPopularMovie(): List<DBMovieList> {
        return daoMovieList.getPopularMovies()
    }

    override suspend fun discoverMovieByGenre(id: Int): List<DBMovieList> {
        return daoMovieList.getMoviesByGenre(id)
    }

    override suspend fun searchMovies(query: String): List<DBMovieList> {
        return daoMovieList.searchMovieList(query)
    }

    override suspend fun getMovieCategory(): List<DBMovieCategory> {
        return daoMovieCategory.getAll()
    }

    override suspend fun setMovieAsFavorite(movie: DBMovieFavorite): Boolean {
        try {
            daoMovieFavorite.addFavorite(movie)
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun removeMovieFromFavorite(movieID: Int): Boolean {
        try {
            daoMovieFavorite.removeFavorite(movieID)
            return true
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFavoriteMovies(): List<MovieFavoriteRelation> {
        return daoMovieFavorite.getAll()
    }

}