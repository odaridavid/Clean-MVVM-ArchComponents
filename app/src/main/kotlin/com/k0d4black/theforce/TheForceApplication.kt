package com.k0d4black.theforce

import android.app.Application
import com.facebook.stetho.Stetho
import com.k0d4black.theforce.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

internal class TheForceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TheForceApplication)
            modules(
                networkModule,
                viewModelsModule,
                localDataSourceModule,
                remoteDataSourceModule,
                useCasesModule
            )
        }

    }

}