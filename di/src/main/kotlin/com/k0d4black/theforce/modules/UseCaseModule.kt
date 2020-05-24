package com.k0d4black.theforce.modules

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import com.k0d4black.theforce.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {

    single(named("search")) { provideSearchUseCase(get()) }

    single(named("species")) { provideSpeciesUseCase(get()) }

    single(named("planet")) { providePlanetUseCase(get()) }

    single(named("films")) { provideFilmsUseCase(get()) }

}

fun provideSearchUseCase(searchRepository: ICharacterSearchRepository): SearchUseCase {
    return SearchCharactersUseCase(searchRepository)
}

fun provideSpeciesUseCase(detailsRepository: ICharacterDetailsRepository): SpeciesUseCase {
    return GetSpeciesUseCase(detailsRepository)
}

fun providePlanetUseCase(detailsRepository: ICharacterDetailsRepository): PlanetUseCase {
    return GetPlanetUseCase(detailsRepository)
}

fun provideFilmsUseCase(detailsRepository: ICharacterDetailsRepository): FilmsUseCase {
    return GetFilmsUseCase(detailsRepository)
}