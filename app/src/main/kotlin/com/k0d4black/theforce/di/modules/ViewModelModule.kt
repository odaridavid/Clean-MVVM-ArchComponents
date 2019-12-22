package com.k0d4black.theforce.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.k0d4black.theforce.di.ViewModelKey
import com.k0d4black.theforce.di.factory.ViewModelFactory
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(CharacterSearchViewModel::class)
    abstract fun bindCharacterSearchViewModel(characterSearchViewModel: CharacterSearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}