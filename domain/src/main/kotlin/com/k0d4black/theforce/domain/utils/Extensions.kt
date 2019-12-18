package com.k0d4black.theforce.domain.utils


/**
 *
 * Creates an extension property on [String] that gets an id extracted from the string url passed.
 *
 */
val String.id: Int
    get() =
        if (this.isNotBlank() && this.contains("-?\\d+(\\.\\d+)?".toRegex()))
            this.replace("[^0-9]".toRegex(), "").toInt()
        else -1
