package com.hanif.nexmedistest.data.db.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.DBMovieList

data class MovieFavoriteRelation(
    @Embedded
    val movieFavorite: DBMovieFavorite,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movie: DBMovieList?
)
