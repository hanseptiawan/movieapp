package com.hanif.nexmedistest.data.repository.datastore

import android.util.Log
import com.hanif.nexmedistest.data.network.response.BaseConfigurationResponse
import com.hanif.nexmedistest.data.network.response.MovieCategoryResponse
import com.hanif.nexmedistest.data.network.response.MovieListResponse
import com.hanif.nexmedistest.data.network.response.base.BaseApiResult
import com.hanif.nexmedistest.data.network.service.ApiServiceInterface
import com.hanif.nexmedistest.data.repository.contract.MovieRemoteDSContract
import javax.inject.Inject

class MovieRemoteDS @Inject constructor(
    private val apiServiceInterface: ApiServiceInterface
): MovieRemoteDSContract {

    override suspend fun getBaseConfiguration(): BaseApiResult<BaseConfigurationResponse> {
        return try {
            val apiResponse = apiServiceInterface.getBaseConfiguration().execute()
            val body = apiResponse.body()
            body!!.getResult(body, apiResponse.isSuccessful)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPopularMovie(): BaseApiResult<MovieListResponse> {
        return try {
            val apiResponse = apiServiceInterface.getPopularMovies().execute()
            val body = apiResponse.body()
            body!!.getResult(body, apiResponse.isSuccessful)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun discoverMovieByGenre(id: Int): BaseApiResult<MovieListResponse> {
        return try {
            val apiResponse = apiServiceInterface.discoverMoviesByGenre(withGenres = id.toString()).execute()
            val body = apiResponse.body()
            body!!.getResult(body, apiResponse.isSuccessful)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchMovies(query: String): BaseApiResult<MovieListResponse> {
        return try {
            val apiResponse = apiServiceInterface.searchMovies(query = query).execute()
            val body = apiResponse.body()
            body!!.getResult(body, apiResponse.isSuccessful)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMovieCategory(): BaseApiResult<MovieCategoryResponse> {
        return try {
            val apiResponse = apiServiceInterface.getMovieCategory().execute()
            val body = apiResponse.body()
            body!!.getResult(body, apiResponse.isSuccessful)
        } catch (e: Exception) {
            throw e
        }
    }

}