package com.hanif.nexmedistest.presentation.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanif.nexmedistest.data.repository.MovieRepository
import com.hanif.nexmedistest.data.repository.base.BaseViewModel
import com.hanif.nexmedistest.data.repository.contract.MovieRepoContract
import com.hanif.nexmedistest.domain.base.BaseLiveDataState
import com.hanif.nexmedistest.domain.model.MovieListView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepoContract
): BaseViewModel(), FavoriteVMContract {

    private val mLiveSetMovieAsFavorite = MutableLiveData<BaseLiveDataState<Boolean>>()
    private val mLiveRemoveMovieFromFavorite = MutableLiveData<BaseLiveDataState<Boolean>>()
    private val mLiveGetFavoriteMovies = MutableLiveData<BaseLiveDataState<List<MovieListView>>>()

    override suspend fun setMovieAsFavorite(movieID: Int) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepository.setMovieAsFavorite(movieID)
                mLiveSetMovieAsFavorite.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveSetMovieAsFavorite.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override suspend fun observeSetMovieAsFavorite(): LiveData<BaseLiveDataState<Boolean>> =
        mLiveSetMovieAsFavorite

    override suspend fun removeMovieFromFavorite(movieID: Int) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepository.setMovieAsFavorite(movieID)
                mLiveRemoveMovieFromFavorite.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveRemoveMovieFromFavorite.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override suspend fun observeRemoveMovieFromFavorite(): LiveData<BaseLiveDataState<Boolean>> =
        mLiveRemoveMovieFromFavorite

    override suspend fun getFavoriteMovies() {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepository.getFavoriteMovies()
                mLiveGetFavoriteMovies.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveGetFavoriteMovies.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override suspend fun observeGetFavoriteMovies(): LiveData<BaseLiveDataState<List<MovieListView>>> =
        mLiveGetFavoriteMovies

}