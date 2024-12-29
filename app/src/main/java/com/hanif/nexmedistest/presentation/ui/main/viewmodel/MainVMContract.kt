package com.hanif.nexmedistest.presentation.ui.main.viewmodel

interface MainVMContract {

    suspend fun getBaseConfiguration()

    suspend fun setAppOffline(isOffline: Boolean)

}