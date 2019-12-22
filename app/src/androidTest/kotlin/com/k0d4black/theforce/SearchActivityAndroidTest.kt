package com.k0d4black.theforce

import android.content.Intent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.data.api.ApiUtils
import com.k0d4black.theforce.features.character_search.SearchActivity
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchActivityAndroidTest {

    private lateinit var server: MockWebServer

    @get:Rule
    var activityRule: ActivityTestRule<SearchActivity> =
        ActivityTestRule(SearchActivity::class.java)

    @Before
    fun setup() {
        server = MockWebServer()
        server.dispatcher =
            StarWarsRequestDispatcher(InstrumentationRegistry.getInstrumentation().context)
        ApiUtils.BASE_URL = server.url("/").toString()
    }

    @Test
    fun testDisplaysDefaultViewOnLaunch() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(R.id.search_tip_text_view)).check(matches(withText(R.string.info_search_tip)))
        activityRule.finishActivity()
    }

    @Test
    fun testDisplaysQueriedDataOnSuccess() {
//        val intent = Intent()
//        activityRule.launchActivity(intent)
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Darth"))
//        activityRule.finishActivity()
    }

    @Test
    fun testDisplaysErrorSnackbarOnError() {
    }

    @Test
    fun testDisplaysNoDataSnackbarOnEmptyDataLoaded() {
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }
}