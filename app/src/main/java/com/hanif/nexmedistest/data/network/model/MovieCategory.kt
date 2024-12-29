package com.hanif.nexmedistest.data.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCategory(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
