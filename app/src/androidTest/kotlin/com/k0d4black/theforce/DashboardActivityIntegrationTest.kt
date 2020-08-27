package com.k0d4black.theforce

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.activities.DashboardActivity
import com.k0d4black.theforce.helpers.ERROR_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.ViewAction
import com.k0d4black.theforce.adapters.SearchedCharacterViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class DashboardActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<DashboardActivity> =
        ActivityTestRule(DashboardActivity::class.java, false, false)

    @get:Rule
    val intentsTestRule = IntentsTestRule(DashboardActivity::class.java)

    @Test
    fun shouldDisplayDataOnSearch() {
        onView(withId(R.id.search_edit_text)).perform(typeText(EXISTING_SEARCH_PARAMS))
        onView(withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldNavigateToCharacterDetailOnItemClickFromSearch() {
        onView(withId(R.id.search_edit_text)).perform(typeText(EXISTING_SEARCH_PARAMS))
        onView(withId(R.id.search_results_recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchedCharacterViewHolder>(
                0, ViewAction.clickChildViewWithId(R.id.more_info_arrow_image_button)
            )
        )
        intended(hasComponent(CHARACTER_DETAIL_ACTIVITY_COMPONENT))
    }

    @Test
    fun shouldDisplayNoFoundMatchesSnackbarOnSearch() {
        onView(withId(R.id.search_edit_text)).perform(typeText(NON_EXISTENT_SEARCH_PARAMS))
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorSnackbarOnSearch() {
        onView(withId(R.id.search_edit_text)).perform(typeText(ERROR_SEARCH_PARAMS))
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldLaunchAboutScreen() {
        onView(withId(R.id.action_settings)).perform(click())
        onView(withId(R.id.about_card_view)).perform(click())
        intended(hasComponent(ABOUT_ACTIVITY_COMPONENT))
    }

    companion object {
        private const val ABOUT_ACTIVITY_COMPONENT =
            "com.k0d4black.theforce.activities.AboutActivity"
        private const val CHARACTER_DETAIL_ACTIVITY_COMPONENT =
            "com.k0d4black.theforce.activities.CharacterDetailsActivity"
    }

}