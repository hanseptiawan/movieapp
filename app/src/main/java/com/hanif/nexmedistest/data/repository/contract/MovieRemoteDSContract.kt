package com.hanif.nexmedistest.data.repository.contract

import com.hanif.nexmedistest.data.network.response.BaseConfigurationResponse
import com.hanif.nexmedistest.data.network.response.MovieCategoryResponse
import com.hanif.nexmedistest.data.network.response.MovieListResponse
import com.hanif.nexmedistest.data.network.response.base.BaseApiResult

interface MovieRemoteDSContract {

    suspend fun getBaseConfiguration(): BaseApiResult<BaseConfigurationResponse>

    suspend fun getPopularMovie(): BaseApiResult<MovieListResponse>

    suspend fun discoverMovieByGenre(id: Int): BaseApiResult<MovieListResponse>

    suspend fun searchMovies(query: String): BaseApiResult<MovieListResponse>

    suspend fun getMovieCategory(): BaseApiResult<MovieCategoryResponse>

}