package com.hanif.nexmedistest.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tblmoviefavorite",
    indices = [Index(value = ["movie_id"], unique = true)]
)
data class DBMovieFavorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieID: Int,
    @ColumnInfo(name = "dt_created") val dtCreated: String,
)
