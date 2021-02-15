package com.k0d4black.theforce.local.features.recentsearch.mappers

import com.k0d4black.theforce.local.features.recentsearch.models.RecentSearchEntity
import com.k0d4black.theforce.shared.model.RecentSearch

class RecentSearchMapper {

    fun mapToDbEntity() {

    }

    fun mapToDomain(recentSearchEntity: RecentSearchEntity): RecentSearch {
        return RecentSearch(
            characterName = "",
            birthYear = ""
        )
    }
}
