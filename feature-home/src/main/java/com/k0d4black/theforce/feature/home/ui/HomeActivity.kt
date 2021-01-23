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
package com.k0d4black.theforce.feature.home.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.k0d4black.theforce.feature.home.R
import com.k0d4black.theforce.shared.android.AppScreen
import com.k0d4black.theforce.shared.android.extensions.navigateToFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    // region Members

    private val homeViewModel: HomeViewModel by viewModel()

    // endregion

    // region Android Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpBottomNavigationView()
        bindViewModel()
        setupDefaultScreen()
    }

    // endregion

    // region OnNavigationItemSelectedListener

    override fun onNavigationItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.favorites -> {
                homeViewModel.navigateToFavorites()
                true
            }
            R.id.search -> {
                homeViewModel.navigateToSearch()
                true
            }
            R.id.settings -> {
                homeViewModel.navigateToSettings()
                true
            }
            else -> false
        }

    // endregion

    // region Private Api

    private fun bindViewModel() {
        homeViewModel.onNavigateToFavorites().observe(this) {
            navigateToFragment(
                appScreen = AppScreen.FAVORITES,
                fragmentContainer = R.id.fragment_container
            )
        }
        homeViewModel.onNavigateToSearch().observe(this) {
            navigateToFragment(
                appScreen = AppScreen.CHARACTER_SEARCH,
                fragmentContainer = R.id.fragment_container
            )
        }
        homeViewModel.onNavigateToSettings().observe(this) {
            navigateToFragment(
                appScreen = AppScreen.SETTINGS,
                fragmentContainer = R.id.fragment_container
            )
        }
    }

    private fun setUpBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.home_bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }


    private fun setupDefaultScreen() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = Class.forName(AppScreen.CHARACTER_SEARCH.classPath).newInstance() as Fragment
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    // endregion
}
