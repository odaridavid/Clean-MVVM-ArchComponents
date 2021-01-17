package com.k0d4black.theforce.feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class HomeViewModel : ViewModel() {

    private val _navigateToFavorites = MutableLiveData<Unit>()
    private val _navigateToSearch = MutableLiveData<Unit>()
    private val _navigateToSettings = MutableLiveData<Unit>()

    fun onNavigateToFavorites(): LiveData<Unit> = _navigateToFavorites

    fun onNavigateToSearch(): LiveData<Unit> = _navigateToSearch

    fun onNavigateToSettings(): LiveData<Unit> = _navigateToSettings

    fun navigateToFavorites() {
        _navigateToFavorites.value = Unit
    }

    fun navigateToSearch() {
        _navigateToSearch.value = Unit
    }

    fun navigateToSettings() {
        _navigateToSettings.value = Unit
    }
}