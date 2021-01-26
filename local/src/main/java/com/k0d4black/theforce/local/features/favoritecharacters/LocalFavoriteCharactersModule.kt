package com.k0d4black.theforce.local.features.favoritecharacters

import com.k0d4black.theforce.local.TheForceDatabase
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersRepository
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersRepositoryImpl
import com.k0d4black.theforce.local.features.favoritecharacters.data.FavoriteCharactersDao
import org.koin.dsl.module

val localFavoriteCharactersModule = module {
    single { provideFavoritesDao(db = get()) }

    single<FavoriteCharactersRepository> {
        FavoriteCharactersRepositoryImpl(
            favoriteCharactersDao = get(),
            favoriteCharacterMapper = get()
        )
    }
}

private fun provideFavoritesDao(db: TheForceDatabase): FavoriteCharactersDao =
    db.favoritesDao()
