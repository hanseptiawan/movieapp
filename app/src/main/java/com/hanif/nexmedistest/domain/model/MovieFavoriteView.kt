package com.hanif.nexmedistest.domain.model

data class MovieFavoriteView(
    val id: Int,
    val movieID: Int,
    val dtCreated: String,
    val movie: MovieListView
)
