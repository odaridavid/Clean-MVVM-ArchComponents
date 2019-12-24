package com.k0d4black.theforce

import com.k0d4black.theforce.di.AppComponent
import com.k0d4black.theforce.di.DaggerTestApplicationComponent

class TheForceTestApplication : TheForceApplication() {

    override fun onCreate() {
        super.onCreate()
        getApplicationComponent().inject(this)
    }

    override fun getApplicationComponent(): AppComponent {
        return DaggerTestApplicationComponent.factory()
            .create(applicationContext)
    }

}