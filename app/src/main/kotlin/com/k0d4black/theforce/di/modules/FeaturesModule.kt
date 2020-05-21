package com.k0d4black.theforce.di.modules

import com.k0d4black.theforce.di.modules.details.CharacterDetailsBindingModule
import com.k0d4black.theforce.di.modules.details.CharacterDetailsModule
import com.k0d4black.theforce.di.modules.search.CharacterSearchBindingModule
import com.k0d4black.theforce.di.modules.search.CharacterSearchModule
import dagger.Module


@Module(
    includes = [
        //Search
        CharacterSearchBindingModule::class,
        CharacterSearchModule::class,

        //Details
        CharacterDetailsBindingModule::class,
        CharacterDetailsModule::class
    ]
)
abstract class FeaturesModule