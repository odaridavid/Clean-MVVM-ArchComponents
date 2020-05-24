package com.k0d4black.theforce.modules

import com.k0d4black.theforce.domain.usecases.GetFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetSpeciesUseCase
import com.k0d4black.theforce.domain.usecases.SearchCharactersUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {

    single(named("search")) { SearchCharactersUseCase(get()) }

    single(named("species")) { GetSpeciesUseCase(get()) }

    single(named("planet")) { GetPlanetUseCase(get()) }

    single(named("films")) { GetFilmsUseCase(get()) }

}
