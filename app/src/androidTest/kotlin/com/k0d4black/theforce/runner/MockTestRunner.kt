package com.k0d4black.theforce.runner

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.security.NetworkSecurityPolicy
import androidx.test.runner.AndroidJUnitRunner
import com.k0d4black.theforce.TheForceTestApplication


class MockTestRunner:AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TheForceTestApplication::class.java.name, context)
    }
}