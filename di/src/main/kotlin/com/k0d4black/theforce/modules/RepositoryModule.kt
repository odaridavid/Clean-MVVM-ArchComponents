package com.k0d4black.theforce.modules

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.repository.CharacterSearchRepository
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single<ICharacterSearchRepository> { CharacterSearchRepository(get()) }

    single<ICharacterDetailsRepository> { CharacterDetailsRepository(get()) }
}