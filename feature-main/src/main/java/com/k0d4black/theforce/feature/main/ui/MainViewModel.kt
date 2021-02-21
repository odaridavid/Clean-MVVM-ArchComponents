package com.k0d4black.theforce.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class MainViewModel : ViewModel() {

    //TODO Replace with flows
    private val _navigateToFavorites = MutableLiveData<Unit>()
    private val _navigateToSearch = MutableLiveData<Unit>()
    private val _navigateToSettings = MutableLiveData<Unit>()

    fun onFavoritesClicked(): LiveData<Unit> = _navigateToFavorites

    fun onSearchClicked(): LiveData<Unit> = _navigateToSearch

    fun onSettingsClicked(): LiveData<Unit> = _navigateToSettings

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