package com.hanif.nexmedistest.data.network.service

import com.hanif.nexmedistest.data.network.response.BaseConfigurationResponse
import com.hanif.nexmedistest.data.network.response.MovieCategoryResponse
import com.hanif.nexmedistest.data.network.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("3/configuration")
    fun getBaseConfiguration(): Call<BaseConfigurationResponse>

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("3/discover/movie")
    fun discoverMoviesByGenre(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("with_genres") withGenres: String = "",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("3/search/movie")
    fun searchMovies(
        @Query("query") query: String = "",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieListResponse>

    @GET("3/genre/movie/list")
    fun getMovieCategory(
        @Query("language") language: String = "en",
    ): Call<MovieCategoryResponse>

}