package com.k0d4black.theforce.commons


sealed class UiState

object Loading : UiState()
class Success<T>(val data: T) : UiState()
class Error(val error: Throwable) : UiState()
