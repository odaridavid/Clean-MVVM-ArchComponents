package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TheForceTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TheForceTestApplication)
            modules(
                fakeNetworkModule,
                viewModelsModule,
                fakeLocalDataSourceModule,
                remoteDataSourceModule,
                useCasesModule
            )
        }
    }
}