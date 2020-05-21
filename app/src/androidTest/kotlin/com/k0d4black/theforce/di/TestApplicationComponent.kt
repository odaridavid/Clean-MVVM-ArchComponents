package com.k0d4black.theforce.di

import android.content.Context
import com.k0d4black.theforce.di.modules.FeaturesModule
import com.k0d4black.theforce.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeStarWarsApiModule::class,
        FeaturesModule::class,
        ViewModelModule::class
    ]
)
interface TestApplicationComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestApplicationComponent
    }

}