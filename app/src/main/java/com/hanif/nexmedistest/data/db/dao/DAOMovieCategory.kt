package com.hanif.nexmedistest.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hanif.nexmedistest.data.db.entity.DBMovieCategory

@Dao
interface DAOMovieCategory {

    @Query("SELECT * FROM tblmoviecategory")
    fun getAll(): List<DBMovieCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DBMovieCategory>)

    @Query("DELETE FROM tblmoviecategory WHERE 1 = 1")
    fun deleteALl()

}