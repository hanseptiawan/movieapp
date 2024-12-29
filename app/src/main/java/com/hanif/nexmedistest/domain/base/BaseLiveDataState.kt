package com.hanif.nexmedistest.domain.base

data class BaseLiveDataState<T>(
    val data: T? = null,
    val isLoading: Boolean = true,
    val errorException: Exception? = null
)
