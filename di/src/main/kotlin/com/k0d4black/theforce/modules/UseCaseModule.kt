package com.k0d4black.theforce.modules

import com.k0d4black.theforce.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {

    single { SearchCharactersUseCase(get()) }

    single { GetSpeciesUseCase(get()) }

    single { GetPlanetUseCase(get()) }

    single { GetFilmsUseCase(get()) }
}