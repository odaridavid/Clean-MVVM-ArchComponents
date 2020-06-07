/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.k0d4black.theforce.utils.CoroutineTestRule
import com.k0d4black.theforce.utils.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest


abstract class BaseViewModelTest : AutoCloseKoinTest() {
    /**
     * Swaps the background executor used by the Architecture Components with a different one which
     * executes each task synchronously.
     **/
    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    abstract fun prepareViewModel(uiState: UiState)
}