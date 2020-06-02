package com.github.odaridavid.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    val title: String,
    val openingCrawl: String,
    val favoriteId: Long = 0L,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)