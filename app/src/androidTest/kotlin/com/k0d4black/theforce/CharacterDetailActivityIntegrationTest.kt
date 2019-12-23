package com.k0d4black.theforce

import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test

class CharacterDetailActivityIntegrationTest {


    @get:Rule
    var activityRule: ActivityTestRule<CharacterDetailActivity> =
        ActivityTestRule(CharacterDetailActivity::class.java)

    @Test
    fun shouldDisplayLoadingOnLaunch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.loading_character_progress_bar))
            .check(matches(isDisplayed()))
        activityRule.finishActivity()
    }

    @Test
    fun shouldLoadDataOnIntentExtraReceived() {
        val intent = Intent().putExtra(CHARACTER_ID_KEY, 2)
        activityRule.launchActivity(intent)
        SystemClock.sleep(8000)
        onView(withId(R.id.character_details_birth_year_title_text_view)).check(matches(isDisplayed()))
        activityRule.finishActivity()
    }
}