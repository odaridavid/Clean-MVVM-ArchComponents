package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.View
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.startActivity
import com.k0d4black.theforce.databinding.ActivitySettingsBinding
import com.k0d4black.theforce.activities.AboutActivity

internal class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.settingsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun openAboutActivity(view: View) = startActivity<AboutActivity>()

}
