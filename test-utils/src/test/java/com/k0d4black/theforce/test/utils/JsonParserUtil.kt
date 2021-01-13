package com.k0d4black.theforce.test.utils

import com.google.common.io.Resources.getResource
import java.io.File

//TODO Use something else
internal fun getJson(path: String): String {
    val uri = getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}
