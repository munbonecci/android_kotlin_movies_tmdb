package com.munbonecci.movies.data.models

import com.google.gson.annotations.SerializedName
import com.munbonecci.movies.domain.models.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)