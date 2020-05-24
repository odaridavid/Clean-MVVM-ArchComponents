package com.k0d4black.theforce.features.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.k0d4black.theforce.R
import com.mikepenz.aboutlibraries.LibsBuilder

class AboutActivity : AppCompatActivity(R.layout.activity_about) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = LibsBuilder()
            .withAboutIconShown(true)
            .supportFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }
}
