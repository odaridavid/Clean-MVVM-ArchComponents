package com.k0d4black.theforce.utils


import android.content.Context

/**
 * Helper function which will load JSON from
 * the path specified
 *
 * @param path : Path of JSON file
 * @return json : JSON from file at given path
 */
fun getJson(path: String, context: Context): String {
    val byteArray = context.resources.assets.open(path).readBytes()
    return String(byteArray)
}