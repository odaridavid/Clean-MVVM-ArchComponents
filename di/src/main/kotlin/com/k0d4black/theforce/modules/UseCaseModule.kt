package com.k0d4black.theforce.modules

import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import com.k0d4black.theforce.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {

    single { provideSearchUseCase(get()) }

    single { GetSpeciesUseCase(get()) }

    single { GetPlanetUseCase(get()) }

    single { GetFilmsUseCase(get()) }

}

fun provideSearchUseCase(searchRepository: ICharacterSearchRepository): SearchUseCase {
    return SearchCharactersUseCase(searchRepository)
}