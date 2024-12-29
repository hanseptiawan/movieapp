package com.hanif.nexmedistest.data.network.response

import com.google.gson.annotations.SerializedName
import com.hanif.nexmedistest.data.network.model.MovieList
import com.hanif.nexmedistest.data.network.response.base.BaseApiResponse

data class MovieListResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MovieList>,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int
): BaseApiResponse()
