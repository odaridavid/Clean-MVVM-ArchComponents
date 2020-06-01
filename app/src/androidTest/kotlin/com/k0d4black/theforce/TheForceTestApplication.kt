package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.fakeNetworkModule
import com.k0d4black.theforce.di.viewModelsModule
import com.k0d4black.theforce.modules.dataSourceModule
import com.k0d4black.theforce.modules.repositoriesModule
import com.k0d4black.theforce.modules.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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