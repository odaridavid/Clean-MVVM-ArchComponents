package com.k0d4black.theforce.local.features.favoritecharacters

import com.k0d4black.theforce.local.TheForceDatabase
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersDataSource
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersLocalDataSource
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersDao
import org.koin.dsl.module

val localFavoriteCharactersModule = module {
    single { provideFavoritesDao(db = get()) }

    single<FavoriteCharactersDataSource> {
        FavoriteCharactersLocalDataSource(
            favoriteCharactersDao = get(),
            favoriteCharacterMapper = get()
        )
    }
}

private fun provideFavoritesDao(db: TheForceDatabase): FavoriteCharactersDao =
    db.favoritesDao()
