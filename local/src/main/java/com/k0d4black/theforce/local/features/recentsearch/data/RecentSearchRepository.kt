package com.k0d4black.theforce.local.features.recentsearch.data

import com.k0d4black.theforce.shared.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {

    fun getRecentSearch(): Flow<Set<RecentSearch>>

}
