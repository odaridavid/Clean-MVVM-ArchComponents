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
package com.k0d4black.theforce.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

internal class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as DashboardActivity).setSupportActionBar(settings_toolbar)
        (requireActivity() as DashboardActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        about_card_view.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionToAboutFragment())
        }
    }
}
