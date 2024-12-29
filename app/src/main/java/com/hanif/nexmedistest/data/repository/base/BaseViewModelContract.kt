package com.hanif.nexmedistest.data.repository.base

import kotlinx.coroutines.Job

interface BaseViewModelContract {

    fun getSupervisorJob(): Job

}