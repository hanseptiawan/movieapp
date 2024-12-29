package com.hanif.nexmedistest.data.network.response

import com.google.gson.annotations.SerializedName
import com.hanif.nexmedistest.data.network.model.MovieCategory
import com.hanif.nexmedistest.data.network.response.base.BaseApiResponse
import kotlinx.serialization.Serializable

@Serializable
data class MovieCategoryResponse(
    @SerializedName("genres") var genres: List<MovieCategory>,
): BaseApiResponse()