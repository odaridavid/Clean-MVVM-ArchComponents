package com.k0d4black.theforce.di.modules

import com.k0d4black.theforce.di.modules.search.CharacterSearchModule
import com.k0d4black.theforce.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [CharacterSearchModule::class])
    abstract fun provideSearchActivity(): SearchActivity

}