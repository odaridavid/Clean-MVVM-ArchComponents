package com.k0d4black.theforce.domain.utils


/**
 *
 * Creates an extension property on [String] that gets an id extracted from the string url passed.
 * if the string has no id or is blank returns a default value of -1.
 *
 */
val String.id: Int
    get() =
        if (this.isNotBlank() && this.contains("-?\\d+(\\.\\d+)?".toRegex()))
            this.replace("[^0-9]".toRegex(), "").toInt()
        else -1
