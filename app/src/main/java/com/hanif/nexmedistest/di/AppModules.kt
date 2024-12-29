package com.hanif.nexmedistest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hanif.nexmedistest.data.db.dao.DAOMovieCategory
import com.hanif.nexmedistest.data.db.dao.DAOMovieFavorite
import com.hanif.nexmedistest.data.db.dao.DAOMovieList
import com.hanif.nexmedistest.data.db.database.DBManager
import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constant.SHARED_PREF_CODE, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferenceManager(sharedPreferences: SharedPreferences): PrefManager {
        return PrefManager(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideAppDB(@ApplicationContext context: Context): DBManager {
        return DBManager.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMovieListDAO(appDB: DBManager): DAOMovieList {
        return appDB.movieListDao()
    }

    @Singleton
    @Provides
    fun provideMovieCategoryDAO(appDB: DBManager): DAOMovieCategory {
        return appDB.movieCategoryDao()
    }

    @Singleton
    @Provides
    fun provideMovieFavoriteDAO(appDB: DBManager): DAOMovieFavorite {
        return appDB.movieFavoriteDao()
    }

}