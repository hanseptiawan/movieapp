package com.hanif.nexmedistest.data.network.response.base

/**
 *Created by Ahmad Hanif S on 27/04/22
 */

sealed class BaseApiResult<out T: Any> {

    class Success<out T: Any>(val data: T): BaseApiResult<T>()

    class Error(val exception: Throwable): BaseApiResult<Nothing>(){
        constructor(message: String?) : this(Throwable(message)){
            this.message = message ?: BaseApiResponse.GENERAL_ERROR
        }

        var message: String = ""
            private set
    }

}