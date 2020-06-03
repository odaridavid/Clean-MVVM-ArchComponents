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
