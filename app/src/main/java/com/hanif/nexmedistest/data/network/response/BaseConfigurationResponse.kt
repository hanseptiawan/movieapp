package com.hanif.nexmedistest.data.network.response

import com.google.gson.annotations.SerializedName
import com.hanif.nexmedistest.data.network.model.BaseConfiguration
import com.hanif.nexmedistest.data.network.response.base.BaseApiResponse
import kotlinx.serialization.Serializable

@Serializable
data class BaseConfigurationResponse(
    @SerializedName("images") var baseConfiguration: BaseConfiguration,
): BaseApiResponse()