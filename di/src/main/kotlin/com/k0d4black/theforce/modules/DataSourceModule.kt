package com.k0d4black.theforce.modules

import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single { CharacterSearchDataSource(get()) }

    single { CharacterDetailsDataSource(get()) }
}