package com.k0d4black.theforce.commons

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Takes in a centimeters value from the domain model and on mapping to the presentation layer
 * model it takes in both centimeters and assigns a converted value to height in inches property.
 **/
fun convertToInches(centimeters: String): String =
    (BigDecimal(centimeters.toDouble() * 0.393701).setScale(3, RoundingMode.HALF_EVEN)).toString()
