package com.k0d4black.theforce.utils

import java.math.BigDecimal
import java.math.RoundingMode


fun convertToInches(centimeters: String): String =
    (BigDecimal(centimeters.toDouble() * 0.393701).setScale(3, RoundingMode.HALF_EVEN)).toString()
