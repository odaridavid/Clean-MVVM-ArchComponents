package com.k0d4black.theforce

import android.app.Application
import com.k0d4black.theforce.di.AppComponent
import com.k0d4black.theforce.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


open class TheForceApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    open lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)

        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}