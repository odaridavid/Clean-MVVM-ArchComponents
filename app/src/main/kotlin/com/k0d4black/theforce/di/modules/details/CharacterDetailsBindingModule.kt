package com.k0d4black.theforce.di.modules.details

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CharacterDetailsBindingModule {
    @Singleton
    @Binds
    abstract fun bindCharacterDetailsRepository(
        characterDetailsRepository: CharacterDetailsRepository
    ): ICharacterDetailsRepository

}