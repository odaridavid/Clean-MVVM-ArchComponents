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
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.k0d4black.theforce.shared.android.AppScreen
import com.k0d4black.theforce.shared.android.R
import com.k0d4black.theforce.shared.android.utils.SdkUtils

fun Activity.showSnackbar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(loadColor(R.color.colorError))
            .setTextColor(loadColor(R.color.colorOnError))
            .show()
    else
        sb.setBackgroundTint(loadColor(R.color.colorSecondary))
            .setTextColor(loadColor(R.color.colorOnSecondary))
            .show()

}

fun Activity.navigateToActivity(
    appScreen: AppScreen,
    intentExtras: ((Intent) -> Unit)? = null
) {
    val intent = Intent(Intent.ACTION_VIEW).setClassName(
        this,
        appScreen.classPath
    )

    intentExtras?.run {
        intentExtras(intent)
    }
    startActivity(intent)
}

fun AppCompatActivity.navigateToFragment(
    appScreen: AppScreen,
    fragmentTransaction: FragmentTransaction.(Fragment) -> Unit
) {
    supportFragmentManager
        .beginTransaction()
        .commitFragment(appScreen = appScreen) { fragment ->
            fragmentTransaction(fragment)
        }
}

fun Activity.setupLightThemeSystemBarsFromApi23() {
    if (SdkUtils.versionFrom(Build.VERSION_CODES.M)) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = getColor(R.color.white)
    }
}
