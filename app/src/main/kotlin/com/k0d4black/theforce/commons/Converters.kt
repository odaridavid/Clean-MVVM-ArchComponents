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
package com.k0d4black.theforce.commons

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Takes in a centimeters value from the domain model and on mapping to the presentation layer
 * model it takes in both centimeters and assigns a converted value to height in inches property.
 **/
internal fun convertToInches(centimeters: String): String =
    (BigDecimal(centimeters.toDouble() * 0.393701).setScale(3, RoundingMode.HALF_EVEN)).toString()

internal fun populationToLong(population: String): Long {
    return if (population.contains("unknown", ignoreCase = true)) 0L else population.toLong()
}