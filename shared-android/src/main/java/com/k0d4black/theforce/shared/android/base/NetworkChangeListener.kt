package com.k0d4black.theforce.shared.android.base

interface NetworkChangeListener {
    fun onNetworkChange(onChange: (isConnected: Boolean) -> Unit)
}
