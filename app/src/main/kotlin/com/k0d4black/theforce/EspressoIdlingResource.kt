package com.k0d4black.theforce

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }

    inline fun <T> wrapEspressoIdlingResource(function: () -> T): T {
        // Espresso does not work well with coroutines yet

        EspressoIdlingResource.increment() // Set app as busy.
        return try {
            function()
        } finally {
            EspressoIdlingResource.decrement() // Set app as idle.
        }

    }
}