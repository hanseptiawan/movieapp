package com.hanif.nexmedistest.data.network.response.base

import com.google.gson.annotations.SerializedName

/**
 *Created by Ahmad Hanif S on 27/04/22
 */
open class BaseApiResponse {

    companion object {
        const val GENERAL_ERROR = "General Error"
        const val NETWORK_ERROR = "Network Error"
        const val SERVER_ERROR = "Server Error"
        const val ERROR_TOKEN = ""
        const val SUCCESS = "SUCCESS"
    }

    @SerializedName("success")
    var success: Boolean? = false
    @SerializedName("message" )
    var message : String?         = null
    @SerializedName("status"  )
    var status  : Int?            = null

    open fun <T : Any> getResult(data: T, isSuccess: Boolean): BaseApiResult<T> {
        return if(isSuccess) {
            BaseApiResult.Success(data)
        } else {
            BaseApiResult.Error(Throwable("error"))
        }

        /*val errorResult = getErrorResult()
        return if (errorResult.isBlank()) ApiResult.Success(data)
        else ApiResult.Error(Throwable(errorResult))*/
    }

    protected open fun getErrorResult(): String {
        val responseCode: Int = status ?: 200
        val msg: String = message ?: ""

        if (msg == "Success") return ""

        return if (responseCode != 200) {
            message ?: GENERAL_ERROR
        } else {
            ""
        }

        //return if (!responseCode.equals(SUCCESS, true)) message ?: GENERAL_ERROR else ""
    }

}