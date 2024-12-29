package com.hanif.nexmedistest.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hanif.nexmedistest.data.db.entity.DBMovieCategory
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.relation.MovieFavoriteRelation

@Dao
interface DAOMovieFavorite {

    @Transaction
    @Query("SELECT * FROM tblmoviefavorite")
    fun getAll(): List<MovieFavoriteRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(list: DBMovieFavorite)

    @Query("DELETE FROM tblmoviefavorite WHERE movie_id = :movieID")
    fun removeFavorite(movieID: Int)

    @Query("DELETE FROM tblmoviefavorite WHERE 1 = 1")
    fun deleteALl()

}