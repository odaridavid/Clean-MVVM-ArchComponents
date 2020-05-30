/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce

import com.google.common.truth.Truth
import com.k0d4black.theforce.commons.ExceptionHandler
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

@RunWith(JUnit4::class)
class ExceptionHandlerTest {

    @Test
    fun `given an exception when is instance of UnknownHostException then get No Internet Connection string `() {
        val message = ExceptionHandler.parse(UnknownHostException())
        Truth.assertThat(message).isEqualTo(R.string.exception_check_internet_connection)
    }

    @Test
    fun `given an exception when is unknown instance then get default string`() {
        val message = ExceptionHandler.parse(Exception())
        Truth.assertThat(message).isEqualTo(R.string.exception_an_error_occurred)
    }
}