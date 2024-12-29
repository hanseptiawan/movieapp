package com.hanif.nexmedistest.domain.model

data class MovieListView(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genreIds: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
)
