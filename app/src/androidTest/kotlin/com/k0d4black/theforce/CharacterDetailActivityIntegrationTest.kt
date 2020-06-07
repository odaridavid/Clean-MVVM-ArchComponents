package com.k0d4black.theforce

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.commons.NavigationUtils
import com.k0d4black.theforce.activities.CharacterDetailActivity
import com.k0d4black.theforce.models.CharacterPresentation
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
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(activityRule.activity.resources.getString(R.string.error_loading_character_details))))
    }

    @Test
    fun shouldLoadDataOnLaunchWithValidCharacterId() {
        val intent = Intent().putExtra(
            NavigationUtils.CHARACTER_PARCEL_KEY,
            CharacterPresentation(
                name = "Luke",
                birthYear = "12BBY",
                heightInCm = "234",
                heightInInches = "544",
                url = "/api/people/1/"
            )
        )
        activityRule.launchActivity(intent)
        onView(withId(R.id.character_details_birth_year_title_text_view)).check(matches(isDisplayed()))
    }

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

}
