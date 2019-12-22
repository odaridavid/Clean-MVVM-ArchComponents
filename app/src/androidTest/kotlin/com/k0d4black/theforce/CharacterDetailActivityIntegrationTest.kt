package com.k0d4black.theforce

import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule

class CharacterDetailActivityIntegrationTest {

    private lateinit var server: MockWebServer

    @get:Rule
    var activityRule: ActivityTestRule<CharacterDetailActivity> =
        ActivityTestRule(CharacterDetailActivity::class.java)

}