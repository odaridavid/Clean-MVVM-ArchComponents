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
package com.k0d4black.theforce.activities

import android.os.Bundle
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.databinding.ActivityAboutBinding
import com.mikepenz.aboutlibraries.LibsBuilder

internal class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.aboutToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = LibsBuilder()
            .withAboutIconShown(true)
            .supportFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }
}
