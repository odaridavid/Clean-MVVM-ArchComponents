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
package com.k0d4black.theforce.feature.about.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.k0d4black.theforce.feature.about.R
import com.k0d4black.theforce.feature.about.databinding.FragmentAboutBinding
import com.k0d4black.theforce.shared.android.base.BindingFragment
import com.mikepenz.aboutlibraries.LibsBuilder

class AboutFragment : BindingFragment<FragmentAboutBinding>() {

    // region Binding Fragment

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAboutBinding =
        FragmentAboutBinding.inflate(layoutInflater, container, false)

    // endregion

    // region Android Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupAboutLibrariesFragment()
    }

    // endregion

    // region Private Api

    private fun setupAboutLibrariesFragment() {
        val aboutLibraryFragment = LibsBuilder()
            .withAboutIconShown(true)
            .supportFragment()

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, aboutLibraryFragment)
            .commit()
    }

    private fun setupActionBar() {
        with(requireActivity() as AppCompatActivity) {
            setSupportActionBar(binding.aboutToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    // endregion
}
