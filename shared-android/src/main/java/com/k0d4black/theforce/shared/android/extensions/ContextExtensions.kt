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
package com.k0d4black.theforce.shared.android.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.loadColor(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}
