package com.k0d4black.theforce

import com.k0d4black.theforce.di.AppComponent
import com.k0d4black.theforce.di.modules.ActivityBuilderModule
import com.k0d4black.theforce.di.modules.StarWarsApiModule
import com.k0d4black.theforce.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        StarWarsApiModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface TestAppComponent : AppComponent {
    fun inject(searchActivityIntegrationTest: SearchActivityIntegrationTest)
}