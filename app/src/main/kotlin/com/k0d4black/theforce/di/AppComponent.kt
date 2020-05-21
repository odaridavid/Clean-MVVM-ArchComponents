package com.k0d4black.theforce.di

import android.content.Context
import com.k0d4black.theforce.TheForceApplication
import com.k0d4black.theforce.di.modules.FeaturesModule
import com.k0d4black.theforce.di.modules.StarWarsApiModule
import com.k0d4black.theforce.di.modules.ViewModelModule
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import com.k0d4black.theforce.features.character_search.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        StarWarsApiModule::class,
        FeaturesModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: TheForceApplication)

    fun inject(characterDetailActivity: CharacterDetailActivity)

    fun inject(searchActivity: SearchActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}

