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
package com.k0d4black.theforce.feature.settings.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.k0d4black.theforce.feature.settings.R
import com.k0d4black.theforce.shared.android.AppScreen
import com.k0d4black.theforce.shared.android.extensions.navigateToActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    // region Android Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO Open About Activity with View Binding
    }

    // endregion

    // region Private Api

    private fun openAboutActivity() =
        requireActivity().navigateToActivity(appScreen = AppScreen.ABOUT)

    // endregion

}
