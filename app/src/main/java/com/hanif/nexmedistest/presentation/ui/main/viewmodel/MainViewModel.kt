package com.hanif.nexmedistest.presentation.ui.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.hanif.nexmedistest.data.repository.MovieRepository
import com.hanif.nexmedistest.data.repository.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): BaseViewModel(), MainVMContract {

    override suspend fun getBaseConfiguration() {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                movieRepository.getBaseConfiguration()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun setAppOffline(isOffline: Boolean) {
        viewModelScope.launch(Dispatchers.Main + getSupervisorJob()) {
            try {
                movieRepository.setAppOffline(isOffline)
            } catch (e: Exception) {
                throw e
            }
        }
    }

}