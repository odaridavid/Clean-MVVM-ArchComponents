package com.k0d4black.theforce.features.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.k0d4black.theforce.R
import com.mikepenz.aboutlibraries.LibsBuilder

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val fragment = LibsBuilder()
            .withAboutIconShown(true)
            .supportFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }
}
