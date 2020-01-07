package com.k0d4black.theforce.commons

/**
 * Created By David Odari
 * On 07/01/20
 *
 **/
sealed class UiState

object Loading : UiState()
object Success : UiState()
class Error(val error: Throwable) : UiState()
