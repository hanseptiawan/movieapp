package com.hanif.nexmedistest.data.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseConfiguration(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("poster_sizes") val posterSize: ArrayList<String>, //array string
)
