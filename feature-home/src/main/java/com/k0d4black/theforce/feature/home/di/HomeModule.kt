package com.k0d4black.theforce.feature.home

import com.k0d4black.theforce.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel {
        HomeViewModel()
    }
}
