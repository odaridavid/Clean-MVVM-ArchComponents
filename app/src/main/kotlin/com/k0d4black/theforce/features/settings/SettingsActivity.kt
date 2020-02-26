package com.k0d4black.theforce.features.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.k0d4black.theforce.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun openAboutActivity(view: View) {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

}
