package com.github.odaridavid.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    val title: String,
    @ColumnInfo(name = "opening_crawl") val openingCrawl: String,
    @ColumnInfo(name = "fav_id") val favoriteId: Long = 0L,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)