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
package com.k0d4black.theforce.shared.android.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.k0d4black.theforce.shared.android.R
import com.k0d4black.theforce.shared.android.utils.SdkUtils

open class BaseActivity : AppCompatActivity() {

    // region Android Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSystemBars()
    }

    // endregion

    // region Private Api

    private fun configSystemBars() {
        if (SdkUtils.versionFrom(Build.VERSION_CODES.M)) {
            setWhiteStatusBar()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun setWhiteStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = getColor(R.color.white)
    }

    // endRegion
}
