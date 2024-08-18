package com.munbonecci.movies.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.munbonecci.movies.db.converter.GenreIdsConverter

@Entity(tableName = "movies")
@TypeConverters(GenreIdsConverter::class)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var movieId: Int = 0,
    var id: Int,
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: List<Int> = emptyList(),
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
)