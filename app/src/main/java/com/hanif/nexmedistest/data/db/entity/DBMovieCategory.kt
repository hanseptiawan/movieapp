package com.hanif.nexmedistest.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblmoviecategory")
data class DBMovieCategory(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name", defaultValue = "") val name: String
)
