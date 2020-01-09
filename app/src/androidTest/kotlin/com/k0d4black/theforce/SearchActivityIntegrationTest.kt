package com.k0d4black.theforce

import android.content.Intent
import android.os.SystemClock
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_search.SearchActivity
import com.k0d4black.theforce.features.character_search.SearchResultAdapter
import com.k0d4black.theforce.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.ViewAction
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<SearchActivity> =
        ActivityTestRule(SearchActivity::class.java, false, false)

    @get:Rule
    val intentsTestRule = IntentsTestRule(SearchActivity::class.java)

    @Before
    override fun setup() {
        super.setup()
        val intent = Intent()
        activityRule.launchActivity(intent)
    }

    @Test
    fun shouldDisplayDefaultViewOnLaunch() {
        onView(withId(R.id.search_tip_text_view)).check(matches(withText(R.string.info_search_tip)))
    }


    @Test
    fun shouldDisplayDataOnSearch() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(EXISTING_SEARCH_PARAMS))
        SystemClock.sleep(2000)
        onView(withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayDefaultTextWhenNoDataFoundOnSearch() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(NON_EXISTENT_SEARCH_PARAMS))
        SystemClock.sleep(3000)
        onView(withId(R.id.search_tip_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldNavigateToCharacterDetailOnItemClick() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(EXISTING_SEARCH_PARAMS))
        SystemClock.sleep(2000)
        onView(withId(R.id.search_results_recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchResultAdapter.SearchedCharacterViewHolder>(
                0, ViewAction.clickChildViewWithId(R.id.more_info_arrow_image_button)
            )
        )
        SystemClock.sleep(2000)
        intended(hasComponent("com.k0d4black.theforce.features.character_details.CharacterDetailActivity"))
    }


    @Test
    fun shouldDisplayErrorMessageOnSearch() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Error"))
        SystemClock.sleep(2000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(isDisplayed()))
    }

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

}