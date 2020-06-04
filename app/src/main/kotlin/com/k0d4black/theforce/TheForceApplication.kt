package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

internal class TheForceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
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