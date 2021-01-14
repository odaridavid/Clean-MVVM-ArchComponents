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
package com.k0d4black.theforce.shared.android

import com.google.common.truth.Truth
import com.k0d4black.theforce.shared.android.utils.ExceptionHandler
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

@RunWith(JUnit4::class)
internal class ExceptionHandlerTest {

    @Test
    fun `when is instance of UnknownHostException then get No Internet Connection string `() {
        // given an Unknown Host Exception
        val exception = UnknownHostException()
        // when we parse it
        val message = ExceptionHandler.parse(UnknownHostException())
        //then the correct string is returned
        Truth.assertThat(message).isEqualTo(R.string.error_check_internet_connection)
    }

    @Test
    fun `when we parse a generic exception is unknown instance then get default string`() {
        //given a generic exception
        val exception = Exception()
        // when we parse the exception
        val message = ExceptionHandler.parse(exception)
        // then the correct string is returned
        Truth.assertThat(message).isEqualTo(R.string.error_oops_error_occured)
    }
}