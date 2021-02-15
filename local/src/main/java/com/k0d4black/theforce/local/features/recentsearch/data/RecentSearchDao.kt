package com.k0d4black.theforce.local.features.recentsearch.data

import androidx.room.Dao
import androidx.room.Query
import com.k0d4black.theforce.local.features.recentsearch.models.RecentSearchEntity

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM recent_search LIMIT 5")
    suspend fun getAll(): List<RecentSearchEntity>

}
