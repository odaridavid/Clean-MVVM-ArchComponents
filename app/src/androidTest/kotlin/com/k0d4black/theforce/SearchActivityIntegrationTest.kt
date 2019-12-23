package com.k0d4black.theforce

import android.content.Intent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.character_search.SearchActivity
import com.k0d4black.theforce.utils.IdlingTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchActivityIntegrationTest : IdlingTest() {

    @get:Rule
    var activityRule: ActivityTestRule<SearchActivity> =
        ActivityTestRule(SearchActivity::class.java)

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        DaggerTestAppComponent.builder()
            .starWarsApiModule(StarWarsApiModuleTest())
            .build()
            .inject(this)
    }

    @Test
    fun testDisplaysDefaultViewOnLaunch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.search_tip_text_view)).check(matches(withText(R.string.info_search_tip)))
        activityRule.finishActivity()
    }

    //TODO Fix flaky test
    @Test
    fun testDisplaysProgressBarOnSearch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Darth"))
        Thread.sleep(2500)
        onView(withId(R.id.loading_search_results_progress_bar)).check(matches(isDisplayed()))
        activityRule.finishActivity()
    }

//
//    @Test
//    fun testDisplaysDataOnSearch() {
//
//        val intent = Intent()
//        activityRule.launchActivity(intent)
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Darth"))
//        SystemClock.sleep(5000)
//        onView(withId(R.id.loading_search_results_progress_bar)).check(matches(isDisplayed()))
//        onView(withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
//        activityRule.finishActivity()
//    }


//    @Test
//    fun testDisplaysErrorSnackbarOnError() {
//    }
//
//    @Test
//    fun testDisplaysNoDataSnackbarOnEmptyDataLoaded() {
//    }


}