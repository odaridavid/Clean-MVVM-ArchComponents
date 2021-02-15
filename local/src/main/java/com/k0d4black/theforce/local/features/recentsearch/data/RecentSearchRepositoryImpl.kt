package com.k0d4black.theforce.local.features.recentsearch.data

import com.k0d4black.theforce.local.features.recentsearch.mappers.RecentSearchMapper
import com.k0d4black.theforce.shared.model.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecentSearchRepositoryImpl(
    private val recentSearchDao: RecentSearchDao,
    private val recentSearchMapper: RecentSearchMapper
) : RecentSearchRepository {

    override fun getRecentSearch(): Flow<Set<RecentSearch>> = flow {
        emit(
            recentSearchDao.getAll().map { recentSearchEntity ->
                recentSearchMapper.mapToDomain(recentSearchEntity = recentSearchEntity)
            }.toSet()
        )
    }

}
