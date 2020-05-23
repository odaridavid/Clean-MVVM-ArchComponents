package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.fakeNetworkModule
import com.k0d4black.theforce.di.modules.viewModelsModule
import com.k0d4black.theforce.modules.dataSourceModule
import com.k0d4black.theforce.modules.repositoriesModule
import com.k0d4black.theforce.modules.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class TheForceTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TheForceTestApplication)
            modules(
                fakeNetworkModule,
                viewModelsModule,
                repositoriesModule,
                dataSourceModule,
                useCasesModule
            )
        }
    }
}