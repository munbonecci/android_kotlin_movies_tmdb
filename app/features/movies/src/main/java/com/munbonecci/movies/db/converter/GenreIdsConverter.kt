package com.munbonecci.movies.db.converter

import androidx.room.TypeConverter

class GenreIdsConverter {

    @TypeConverter
    fun fromString(value: String?): List<Int> {
        if (value.isNullOrEmpty()) {
            return emptyList()
        }
        return value.split(",").map { it.trim().toInt() }
    }

    @TypeConverter
    fun toString(value: List<Int>): String {
        return value.joinToString(",")
    }
}