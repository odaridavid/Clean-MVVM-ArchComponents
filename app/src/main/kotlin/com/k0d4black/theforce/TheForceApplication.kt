package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


open class TheForceApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory()
            .create(applicationContext)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}