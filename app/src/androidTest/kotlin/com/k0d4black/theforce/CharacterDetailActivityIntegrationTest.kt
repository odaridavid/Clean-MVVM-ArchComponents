package com.k0d4black.theforce

import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import com.k0d4black.theforce.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.utils.CHARACTER_ID_KEY
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CharacterDetailActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<CharacterDetailActivity> =
        ActivityTestRule(CharacterDetailActivity::class.java, false, false)


    @Test
    fun shouldDisplayErrorOnLaunchWithDefaultId() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        SystemClock.sleep(1000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("HTTP 404 Client Error")))
    }

    @Test
    fun shouldLoadDataOnLaunchWithValidCharacterId() {
        val intent = Intent().putExtra(CHARACTER_ID_KEY, EXISTING_CHARACTER_ID)
        activityRule.launchActivity(intent)
        SystemClock.sleep(2000)
        onView(withId(R.id.character_details_birth_year_title_text_view)).check(matches(isDisplayed()))
    }

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

}
