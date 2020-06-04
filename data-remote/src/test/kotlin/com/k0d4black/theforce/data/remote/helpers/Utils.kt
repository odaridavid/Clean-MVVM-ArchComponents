package com.k0d4black.theforce.data.remote.helpers


import com.google.common.io.Resources.getResource
import java.io.File

/**
 * Helper function which will load JSON from
 * the path specified
 *
 * @param path : Path of JSON file
 * @return json : JSON from file at given path
 */

internal fun getJson(path: String): String {
    val uri = getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}