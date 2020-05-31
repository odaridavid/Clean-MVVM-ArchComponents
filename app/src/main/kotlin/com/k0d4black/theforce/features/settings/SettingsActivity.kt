package com.k0d4black.theforce.features.settings

import android.os.Bundle
import android.view.View
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.startActivity

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun openAboutActivity(view: View) {
        startActivity<AboutActivity>()
    }
}
