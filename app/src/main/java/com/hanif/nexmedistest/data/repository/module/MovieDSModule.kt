package com.hanif.nexmedistest.data.repository.module

import com.hanif.nexmedistest.data.db.dao.DAOMovieCategory
import com.hanif.nexmedistest.data.db.dao.DAOMovieFavorite
import com.hanif.nexmedistest.data.db.dao.DAOMovieList
import com.hanif.nexmedistest.data.network.service.ApiServiceInterface
import com.hanif.nexmedistest.data.repository.contract.MovieLocalDSContract
import com.hanif.nexmedistest.data.repository.contract.MovieRemoteDSContract
import com.hanif.nexmedistest.data.repository.datastore.MovieLocalDS
import com.hanif.nexmedistest.data.repository.datastore.MovieRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieDSModule {

    @Provides
    fun provideMovieLocalDS(
        daoMovieList: DAOMovieList,
        daoMovieCategory: DAOMovieCategory,
        daoMovieFavorite: DAOMovieFavorite
    ): MovieLocalDSContract {
        return MovieLocalDS(
            daoMovieList = daoMovieList,
            daoMovieCategory = daoMovieCategory,
            daoMovieFavorite = daoMovieFavorite
        )
    }

    @Provides
    fun provideMovieRemoteDS(
        apiServiceInterface: ApiServiceInterface
    ): MovieRemoteDSContract {
        return MovieRemoteDS(
            apiServiceInterface = apiServiceInterface
        )
    }

}