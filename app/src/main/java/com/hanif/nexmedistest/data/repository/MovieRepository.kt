package com.hanif.nexmedistest.data.repository

import android.util.Log
import com.hanif.nexmedistest.data.db.entity.DBMovieCategory
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.DBMovieList
import com.hanif.nexmedistest.data.db.entity.relation.MovieFavoriteRelation
import com.hanif.nexmedistest.data.network.model.MovieCategory
import com.hanif.nexmedistest.data.network.model.MovieList
import com.hanif.nexmedistest.data.network.response.base.BaseApiResult
import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.data.repository.contract.MovieLocalDSContract
import com.hanif.nexmedistest.data.repository.contract.MovieRemoteDSContract
import com.hanif.nexmedistest.data.repository.contract.MovieRepoContract
import com.hanif.nexmedistest.domain.model.MovieCategoryView
import com.hanif.nexmedistest.domain.model.MovieListView
import com.hanif.nexmedistest.utils.DateUtils
import com.hanif.nexmedistest.utils.Extensions.convertStringToArrayInt
import com.hanif.nexmedistest.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val prefUtils: PrefManager,
    private val movieLocalDS: MovieLocalDSContract,
    private val movieRemoteDS: MovieRemoteDSContract
): MovieRepoContract {

    override suspend fun setAppOffline(isOffline: Boolean) =
        withContext(Dispatchers.IO) {
            prefUtils.isOffline = isOffline
        }

    override suspend fun getBaseConfiguration() =
        withContext(Dispatchers.IO) {
            if (networkUtils.isNetworkConnected()) {
                when(val result = movieRemoteDS.getBaseConfiguration()) {
                    is BaseApiResult.Success -> {
                        prefUtils.imageBaseUrl = result.data.baseConfiguration.baseUrl
                        prefUtils.imagePosterSize = result.data.baseConfiguration.posterSize[2]
                    }

                    is BaseApiResult.Error -> {

                    }
                }
            }
    }

    override suspend fun getPopularMovie(): List<MovieListView> =
        withContext(Dispatchers.IO) {

            if (networkUtils.isNetworkConnected() && !prefUtils.isOffline) {
                when(val result = movieRemoteDS.getPopularMovie()) {
                    is BaseApiResult.Success -> {
                        movieLocalDS.insertAllMovies(convertMovieDBEntity(result.data.results))
                        return@withContext convertMovieDBView(movieLocalDS.getPopularMovie())
                    }

                    is BaseApiResult.Error -> {
                        return@withContext emptyList<MovieListView>()
                    }
                }
            } else {
                return@withContext convertMovieDBView(movieLocalDS.getPopularMovie())
            }
    }

    private fun convertMovieDBView(results: List<DBMovieList>): List<MovieListView> {
        return ArrayList<MovieListView>().apply {
            results.forEach { data ->
                this.add(
                    MovieListView(
                        id = data.id,
                        adult = data.adult,
                        backdrop_path = data.backdrop_path,
                        genreIds = arrayListOf(),
                        original_language = data.original_language,
                        original_title = data.original_title,
                        overview = data.overview,
                        popularity = data.popularity,
                        poster_path = data.poster_path,
                        release_date = data.release_date,
                        title = data.title,
                        vote_average = data.vote_average,
                        vote_count = data.vote_count,
                    )
                )
            }
        }
    }

    private fun convertMovieDBEntity(results: List<MovieList>): List<DBMovieList> {
        return ArrayList<DBMovieList>().apply {
            results.forEach { data ->
                this.add(
                    DBMovieList(
                        id = data.id,
                        adult = data.adult,
                        backdrop_path = data.backdropPath,
                        genre_ids = "",
                        original_language = data.originalLanguage,
                        original_title = data.originalTitle,
                        overview = data.overview,
                        popularity = data.popularity,
                        poster_path = data.poster_path,
                        release_date = data.releaseDate,
                        title = data.title,
                        vote_average = data.voteAverage,
                        vote_count = data.voteCount,
                )
                )
            }
        }
    }

    override suspend fun discoverMovieByGenre(id: Int): List<MovieListView> =
        withContext(Dispatchers.IO) {
            if (networkUtils.isNetworkConnected() && !prefUtils.isOffline) {
                when(val result = movieRemoteDS.discoverMovieByGenre(id)) {
                    is BaseApiResult.Success -> {
                        Log.d("MOVIE_LIST_REPO", "discoverMovieByGenre: ${result.data.results.size}")
                        movieLocalDS.insertAllMovies(convertMovieDBEntity(result.data.results))
                        return@withContext convertMovieDBView(movieLocalDS.discoverMovieByGenre(id))
                    }

                    is BaseApiResult.Error -> {
                        return@withContext emptyList<MovieListView>()
                    }
                }
            } else {
                return@withContext convertMovieDBView(movieLocalDS.discoverMovieByGenre(id))
            }
    }

    override suspend fun searchMovies(query: String): List<MovieListView> =
        withContext(Dispatchers.IO) {
            if (networkUtils.isNetworkConnected() && !prefUtils.isOffline) {
                when(val result = movieRemoteDS.searchMovies(query)) {
                    is BaseApiResult.Success -> {
                        movieLocalDS.insertAllMovies(convertMovieDBEntity(result.data.results))
                        return@withContext convertMovieDBView(movieLocalDS.searchMovies(query))
                    }

                    is BaseApiResult.Error -> {
                        return@withContext emptyList<MovieListView>()
                    }
                }
            } else {
                return@withContext convertMovieDBView(movieLocalDS.searchMovies(query))
            }
    }

    override suspend fun getMovieCategory(): List<MovieCategoryView> =
        withContext(Dispatchers.IO) {
            if (networkUtils.isNetworkConnected() && !prefUtils.isOffline) {
                when(val result = movieRemoteDS.getMovieCategory()) {
                    is BaseApiResult.Success -> {
                        movieLocalDS.insertAllCategory(convertCategoryDBEntity(result.data.genres))
                        return@withContext convertCategoryDBView(movieLocalDS.getMovieCategory())
                    }

                    is BaseApiResult.Error -> {
                        return@withContext emptyList<MovieCategoryView>()
                    }
                }
            } else {
                return@withContext convertCategoryDBView(movieLocalDS.getMovieCategory())
            }
    }

    private fun convertCategoryDBView(movieCategory: List<DBMovieCategory>): List<MovieCategoryView> {
        return ArrayList<MovieCategoryView>().apply {
            this.add(
                MovieCategoryView(
                    id = -999,
                    name = "Popular",
                    isSelected = true
                )
            )

            movieCategory.forEach { data ->
                this.add(
                    MovieCategoryView(
                        id = data.id,
                        name = data.name,
                        isSelected = false
                    )
                )
            }
        }
    }

    private fun convertCategoryDBEntity(genres: List<MovieCategory>): List<DBMovieCategory> {
        return ArrayList<DBMovieCategory>().apply {
            genres.forEach { data ->
                this.add(
                    DBMovieCategory(
                        id = data.id,
                        name = data.name
                    )
                )
            }
        }
    }

    override suspend fun setMovieAsFavorite(movieID: Int): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext movieLocalDS.setMovieAsFavorite(
                DBMovieFavorite(
                    movieID = movieID,
                    dtCreated = DateUtils().getDateWithFormat(DateUtils.DATE_NUMBERS_ONLY)
                )
            )
    }

    override suspend fun removeMovieFromFavorite(movieID: Int): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext movieLocalDS.removeMovieFromFavorite(
                movieID = movieID
            )
    }

    override suspend fun getFavoriteMovies(): List<MovieListView> =
        withContext(Dispatchers.IO) {
            return@withContext convertFavoriteView(movieLocalDS.getFavoriteMovies())
        }

    private fun convertFavoriteView(favoriteMovies: List<MovieFavoriteRelation>): List<MovieListView> {
        return ArrayList<MovieListView>().apply {
            favoriteMovies.forEach { data ->
                this.add(
                    MovieListView(
                        id = data.movie?.id?: 0,
                        adult = data.movie?.adult?: false,
                        backdrop_path = data.movie?.backdrop_path?: "",
                        genreIds = convertStringToArrayInt(data.movie?.genre_ids?: ""),
                        original_language = data.movie?.original_language?: "",
                        original_title = data.movie?.original_title?: "",
                        overview = data.movie?.overview?: "",
                        popularity = data.movie?.popularity?: 0.0,
                        poster_path = data.movie?.poster_path?: "",
                        release_date = data.movie?.release_date?: "",
                        title = data.movie?.title?: "",
                        vote_average = data.movie?.vote_average?: 0.0,
                        vote_count = data.movie?.vote_count?: 0,
                    )
                )
            }
        }
    }

}
