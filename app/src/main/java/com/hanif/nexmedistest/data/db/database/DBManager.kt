package com.hanif.nexmedistest.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hanif.nexmedistest.data.db.dao.DAOMovieCategory
import com.hanif.nexmedistest.data.db.dao.DAOMovieFavorite
import com.hanif.nexmedistest.data.db.dao.DAOMovieList
import com.hanif.nexmedistest.data.db.entity.DBMovieCategory
import com.hanif.nexmedistest.data.db.entity.DBMovieFavorite
import com.hanif.nexmedistest.data.db.entity.DBMovieList
import com.hanif.nexmedistest.utils.Constant

@Database(
    entities = [
        DBMovieList::class,
        DBMovieCategory::class,
        DBMovieFavorite::class,
    ],
    version = 1,
    exportSchema = true,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ]
)

abstract class DBManager: RoomDatabase() {

    abstract fun movieListDao(): DAOMovieList
    abstract fun movieCategoryDao(): DAOMovieCategory
    abstract fun movieFavoriteDao(): DAOMovieFavorite

    companion object {
        private var INSTANCE: DBManager? = null

        fun getDatabase(context: Context): DBManager {
            val currentInstance = INSTANCE
            if (currentInstance != null) return currentInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBManager::class.java,
                    Constant.LOCAL_DATABASE
                )
                    .build()

                INSTANCE = instance

                return instance
            }
        }
    }



}