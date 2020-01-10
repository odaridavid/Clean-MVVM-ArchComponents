package com.k0d4black.theforce.di.modules

import com.k0d4black.theforce.di.ActivityScope
import com.k0d4black.theforce.di.modules.details.CharacterDetailsBindingModule
import com.k0d4black.theforce.di.modules.details.CharacterDetailsModule
import com.k0d4black.theforce.di.modules.search.CharacterSearchBindingModule
import com.k0d4black.theforce.di.modules.search.CharacterSearchModule
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import com.k0d4black.theforce.features.character_search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [CharacterSearchBindingModule::class, CharacterDetailsBindingModule::class])
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [CharacterSearchModule::class])
    abstract fun provideSearchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [CharacterDetailsModule::class])
    abstract fun provideCharacterDetailActivity(): CharacterDetailActivity

}