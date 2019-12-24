package com.k0d4black.theforce

import android.content.Intent
import android.os.SystemClock
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_search.SearchActivity
import com.k0d4black.theforce.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.helpers.NON_EXISTENT_SEARCH_PARAMS
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<SearchActivity> =
        ActivityTestRule(SearchActivity::class.java, false, false)

    @Test
    fun shouldDisplayDefaultViewOnLaunch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.search_tip_text_view)).check(matches(withText(R.string.info_search_tip)))
        activityRule.finishActivity()
    }


    @Test
    fun shouldDisplayDataOnSearch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(EXISTING_SEARCH_PARAMS))
        SystemClock.sleep(2000)
        onView(withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
        activityRule.finishActivity()
    }

    @Test
    fun shouldDisplayDefaultTextWhenNoDataFoundOnSearch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(NON_EXISTENT_SEARCH_PARAMS))
        SystemClock.sleep(2000)
        onView(withId(R.id.search_tip_text_view)).check(matches(isDisplayed()))
        activityRule.finishActivity()
    }


}