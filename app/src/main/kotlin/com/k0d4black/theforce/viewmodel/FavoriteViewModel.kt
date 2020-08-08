/*
*
* Copyright 2020 David Odari
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except 
* in compliance with the License. You may obtain a copy of the License at
*
*          http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed under the License 
* is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
* or implied. See the License for the specific language governing permissions and limitations under
* the License.
*
*/
package com.k0d4black.theforce.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.models.Result
import com.k0d4black.theforce.domain.usecases.DeleteFavoriteByNameBaseUseCase
import com.k0d4black.theforce.domain.usecases.GetFavoriteByNameBaseUseCase
import com.k0d4black.theforce.domain.usecases.InsertFavoriteBaseUseCase
import com.k0d4black.theforce.mappers.toDomain
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.Error
import com.k0d4black.theforce.models.states.FavoriteViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

internal class FavoriteViewModel(
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameBaseUseCase,
    private val insertFavoriteUseCase: InsertFavoriteBaseUseCase,
    private val getFavoriteByNameUseCase: GetFavoriteByNameBaseUseCase
) : BaseViewModel() {

    // region Members

    private var saveFavoriteJob: Job? = null
    private var deleteFavoriteJob: Job? = null
    private var getFavoriteJob: Job? = null

    val favoriteViewState: LiveData<FavoriteViewState>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<FavoriteViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharacterDetailVM", "$exception")
        val message = ExceptionHandler.parse(exception)
        _favoriteViewState.value = _favoriteViewState.value?.copy(error = Error(message))
    }

    // endregion

    // region Constructor

    init {
        _favoriteViewState.value = FavoriteViewState(isFavorite = false, error = null)
    }

    // endregion

    // region Android API

    override fun onCleared() {
        super.onCleared()
        saveFavoriteJob?.cancel()
        getFavoriteJob?.cancel()
        deleteFavoriteJob?.cancel()
    }

    // endregion

    // region Public API

    fun saveFavorite(favoritePresentation: FavoritePresentation) {
        saveFavoriteJob = launchCoroutine {
            insertFavoriteUseCase(favoritePresentation.toDomain()).collect { result ->
                if (result == Result.SUCCESS) {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = true)
                } else {
                    Log.i(this.javaClass.simpleName, "Saving Favorites Failed")
                }
            }
        }
    }

    fun deleteFavorite(name: String) {
        deleteFavoriteJob = launchCoroutine {
            deleteFavoriteByNameUseCase(name).collect { row ->
                if (row == 1) {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = false)
                }
            }
        }
    }

    fun getFavorite(name: String) {
        getFavoriteJob = launchCoroutine {
            getFavoriteByNameUseCase(name).collect { fav ->
                fav?.run {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }

    // endregion
}