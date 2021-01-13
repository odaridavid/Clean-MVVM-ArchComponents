package com.k0d4black.theforce.local.core.favoritecharacters

import com.k0d4black.theforce.local.core.TheForceDatabase
import com.k0d4black.theforce.local.core.favoritecharacters.data.FavoriteCharactersRepository
import com.k0d4black.theforce.local.core.favoritecharacters.data.FavoriteCharactersRepositoryImpl
import com.k0d4black.theforce.local.core.favoritecharacters.data.FavoriteCharactersDao
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
