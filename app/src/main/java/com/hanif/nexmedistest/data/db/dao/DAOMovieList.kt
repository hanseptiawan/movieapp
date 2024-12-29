package com.hanif.nexmedistest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanif.nexmedistest.data.db.entity.DBMovieList

@Dao
interface DAOMovieList {

    @Query("SELECT * FROM tblmovielist")
    fun getPopularMovies(): List<DBMovieList>

    @Query("SELECT * FROM tblmovielist WHERE genre_ids LIKE '%' || :genre")
    fun getMoviesByGenre(genre: Int): List<DBMovieList>

    @Query("SELECT * FROM tblmovielist WHERE title %:keywords")
    fun searchMovieList(keywords: String): List<DBMovieList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DBMovieList>)

    @Query("DELETE FROM tblmovielist WHERE 1 = 1")
    fun deleteALl()

}