package com.k0d4black.theforce.feature.characterdetails.di

import com.k0d4black.theforce.feature.characterdetails.ui.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCharacterDetailsModule = module {
     viewModel {
         CharacterDetailsViewModel(
             planetRepository = get(),
             filmRepository = get(),
             specieRepository = get(),
             favoriteCharactersRepository = get()
         )
     }
}
