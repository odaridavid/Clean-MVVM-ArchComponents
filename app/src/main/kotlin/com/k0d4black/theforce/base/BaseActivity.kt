package com.k0d4black.theforce.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.k0d4black.theforce.commons.NetworkUtils
import com.k0d4black.theforce.commons.versionFrom

internal open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatusBar()
    }

    private fun whiteStatusBar() {
        if (versionFrom(Build.VERSION_CODES.M)) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = getColor(android.R.color.white)
        }
    }

    protected fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->
                block(isConnected)
            })
    }
}