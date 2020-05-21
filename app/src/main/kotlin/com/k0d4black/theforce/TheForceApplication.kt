package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.AppComponent
import com.k0d4black.theforce.di.DaggerAppComponent


open class TheForceApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)

        getApplicationComponent().inject(this)
    }

    open fun getApplicationComponent(): AppComponent = appComponent

}