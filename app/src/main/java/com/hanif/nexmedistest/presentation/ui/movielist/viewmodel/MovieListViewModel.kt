package com.hanif.nexmedistest.presentation.ui.movielist.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanif.nexmedistest.data.repository.base.BaseViewModel
import com.hanif.nexmedistest.data.repository.contract.MovieRepoContract
import com.hanif.nexmedistest.domain.base.BaseLiveDataState
import com.hanif.nexmedistest.domain.model.MovieCategoryView
import com.hanif.nexmedistest.domain.model.MovieListView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepo: MovieRepoContract,
): BaseViewModel(), MovieListVMContract {

    private var mLiveSetMovieAsFavorite = MutableLiveData<BaseLiveDataState<Boolean>>()
    private var mLiveMovies = MutableLiveData<BaseLiveDataState<List<MovieListView>>>()
    private var mLiveMovieCategory = MutableLiveData<BaseLiveDataState<List<MovieCategoryView>>>()

    val searchQuery = mutableStateOf("")
    var selectedCategory = mutableStateOf<MovieCategoryView?>(null)

    init {
        getPopularMovie()
        getMovieCategory()
    }

    override fun getPopularMovie() {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepo.getPopularMovie()
                mLiveMovies.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveMovies.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override fun discoverMovieByGenre(id: Int) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepo.discoverMovieByGenre(id)
                mLiveMovies.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveMovies.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepo.searchMovies(query)
                mLiveMovies.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveMovies.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override fun observeMovies(): LiveData<BaseLiveDataState<List<MovieListView>>> = mLiveMovies

    override fun getMovieCategory() {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepo.getMovieCategory()
                mLiveMovieCategory.value = BaseLiveDataState(
                    data = result,
                    isLoading = false,
                    errorException = null
                )
            } catch (e: Exception) {
                mLiveMovieCategory.value = BaseLiveDataState(
                    data = null,
                    isLoading = false,
                    errorException = e
                )
            }
        }
    }

    override fun observeMovieCategory(): LiveData<BaseLiveDataState<List<MovieCategoryView>>> = mLiveMovieCategory

    override fun setMovieAsFavorite(movieID: Int) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                val result = movieRepo.setMovieAsFavorite(movieID)
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

    override fun observeSetMovieAsFavorite(): LiveData<BaseLiveDataState<Boolean>> = mLiveSetMovieAsFavorite

}