package com.k0d4black.theforce.feature.main

import com.k0d4black.theforce.feature.characterdetails.di.featureCharacterDetailsModule
import com.k0d4black.theforce.feature.charactersearch.di.featureCharacterSearchModule
import com.k0d4black.theforce.feature.charactersearchresults.di.featureCharacterSearchResultsModule
import com.k0d4black.theforce.feature.favoritecharacters.di.featureFavoritesModule
import com.k0d4black.theforce.feature.main.di.featureMainModule

val featureModules = listOf(
    featureCharacterDetailsModule,
    featureCharacterSearchModule,
    featureCharacterSearchResultsModule,
    featureFavoritesModule,
    featureMainModule
)