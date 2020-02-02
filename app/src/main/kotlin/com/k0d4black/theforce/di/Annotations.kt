package com.k0d4black.theforce.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Annotation for having custom keys for viewmodel factory map
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Scopes dependencies injected into activity to be preserved as long as the activity component is available
 */
@Scope
annotation class ActivityScope
