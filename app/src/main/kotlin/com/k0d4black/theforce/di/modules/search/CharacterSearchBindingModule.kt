package com.k0d4black.theforce.di.modules.search

import com.k0d4black.theforce.data.repository.StarWarsCharacterSearchRepository
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CharacterSearchBindingModule {

    @Singleton
    @Binds
    abstract fun bindCharacterSearchRepository(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): ICharacterSearchRepository

}