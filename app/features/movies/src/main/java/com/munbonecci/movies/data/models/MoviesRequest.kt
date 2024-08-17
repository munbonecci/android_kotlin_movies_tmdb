package com.munbonecci.movies.data.models

import com.google.gson.annotations.SerializedName
import com.munbonecci.movies.BuildConfig

data class MoviesRequest(
    @SerializedName("api_key")
    val apiKey: String = BuildConfig.apiKey,
    @SerializedName("include_adult")
    val includeAdult: Boolean = false,
    @SerializedName("include_video")
    val includeVideo: Boolean = false,
    val language: String = "en-US",
    val page: Int = 1,
    @SerializedName("sort_by")
    val sortBy: String = "popularity.desc",
    @SerializedName("with_release_type")
    val withReleaseType: String = "2|3",
    @SerializedName("min_date")
    val minDate: String? = null,
    @SerializedName("max_date")
    val maxDate: String? = null
)