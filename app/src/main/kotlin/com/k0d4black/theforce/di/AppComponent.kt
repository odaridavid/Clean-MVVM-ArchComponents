package com.k0d4black.theforce.di

import android.content.Context
import com.k0d4black.theforce.TheForceApplication
import com.k0d4black.theforce.di.modules.ActivityBuilderModule
import com.k0d4black.theforce.di.modules.StarWarsApiModule
import com.k0d4black.theforce.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        StarWarsApiModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: TheForceApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}

