package com.hanif.nexmedistest.data.repository.module

import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.data.repository.MovieRepository
import com.hanif.nexmedistest.data.repository.contract.MovieLocalDSContract
import com.hanif.nexmedistest.data.repository.contract.MovieRemoteDSContract
import com.hanif.nexmedistest.data.repository.contract.MovieRepoContract
import com.hanif.nexmedistest.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieRepoModule {

    @Provides
    fun provideMovieRepository(
        networkUtils: NetworkUtils,
        prefUtils: PrefManager,
        movieLocalDS: MovieLocalDSContract,
        movieRemoteDS: MovieRemoteDSContract
    ): MovieRepoContract {
        return MovieRepository(
            networkUtils = networkUtils,
            prefUtils = prefUtils,
            movieLocalDS = movieLocalDS,
            movieRemoteDS = movieRemoteDS
        )
    }

}