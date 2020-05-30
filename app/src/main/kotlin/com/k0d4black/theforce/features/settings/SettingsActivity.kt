package com.k0d4black.theforce.features.settings

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.startActivity

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    fun openAboutActivity(view: View) {
        startActivity<AboutActivity>()
    }
}
