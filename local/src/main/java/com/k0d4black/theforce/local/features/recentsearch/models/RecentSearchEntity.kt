package com.k0d4black.theforce.local.features.recentsearch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recent_search")
data class RecentSearchEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "character_name") val characterName: String,
    @ColumnInfo(name = "birth_year") val birthYear: String,
    @ColumnInfo(name = "timestamp") val timeStamp: Date? = null
)
