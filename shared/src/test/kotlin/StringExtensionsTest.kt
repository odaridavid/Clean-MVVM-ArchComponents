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

import com.google.common.truth.Truth
import com.k0d4black.theforce.shared.extensions.convertCentimetresToInches
import com.k0d4black.theforce.shared.extensions.convertPopulationToLong
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StringExtensionsTest {

    @Test
    fun `given centimeters value then convert to inches `() {
        val inches = "120".convertCentimetresToInches()
        Truth.assertThat(inches).isEqualTo("47.244")
    }

    @Test
    fun `given unknown population then return zero`() {
        val population = "unknown".convertPopulationToLong()
        Truth.assertThat(population).isEqualTo(0L)
    }

    @Test
    fun `given a population with integral value then return population`() {
        val population = "100000".convertPopulationToLong()
        Truth.assertThat(population).isEqualTo(100000L)
    }
}
