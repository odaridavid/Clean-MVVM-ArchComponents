package com.k0d4black.theforce.shared.android.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.k0d4black.theforce.shared.android.utils.NetworkUtils

interface NetworkChangeListener {
    fun onNetworkChange(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        onChange: (isConnected: Boolean) -> Unit
    ) {
        NetworkUtils.getNetworkStatus(context)
            .observe(
                lifecycleOwner,
                Observer { isConnected ->
                    onChange(isConnected)
                }
            )
    }
}
