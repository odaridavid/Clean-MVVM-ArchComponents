package com.k0d4black.theforce.feature.main.di

import com.k0d4black.theforce.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMainModule = module {
    viewModel {
        MainViewModel()
    }
}
