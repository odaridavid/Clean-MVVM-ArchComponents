package com.k0d4black.theforce

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k0d4black.theforce.features.DashboardActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class DashboardActivityIntegrationTest : BaseTest() {
    //TODO Update search views with edit tezt
    @get:Rule
    var activityRule: ActivityTestRule<DashboardActivity> =
        ActivityTestRule(DashboardActivity::class.java, false, false)

    @get:Rule
    val intentsTestRule = IntentsTestRule(DashboardActivity::class.java)

    @Before
    override fun setup() {
        super.setup()
        val intent = Intent()
        activityRule.launchActivity(intent)
    }


//    @Test
//    fun shouldDisplayDataOnSearch() {
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(typeText(EXISTING_SEARCH_PARAMS))
//        onView(withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldDisplayDefaultTextWhenNoDataFoundOnSearch() {
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(typeText(NON_EXISTENT_SEARCH_PARAMS))
//        SystemClock.sleep(2000)
//        onView(withId(R.id.search_tip_text_view)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun shouldNavigateToCharacterDetailOnItemClick() {
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(typeText(EXISTING_SEARCH_PARAMS))
//        SystemClock.sleep(1500)
//        onView(withId(R.id.search_results_recycler_view)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<SearchResultAdapter.SearchedCharacterViewHolder>(
//                0, ViewAction.clickChildViewWithId(R.id.more_info_arrow_image_button)
//            )
//        )
//        SystemClock.sleep(1500)
//        intended(hasComponent(CHARACTER_DETAIL_ACTIVITY_COMPONENT))
//    }
//
//
//    @Test
//    fun shouldDisplayErrorMessageOnSearch() {
//        onView(withId(R.id.action_search)).perform(click())
//        onView(isAssignableFrom(EditText::class.java)).perform(
//            typeText(
//                EXCEPTION_THROWN_SEARCH_PARAMS
//            )
//        )
//        SystemClock.sleep(2000)
//        onView(withId(com.google.android.material.R.id.snackbar_text))
//            .check(matches(isDisplayed()))
//    }

    @Test
    fun shouldLaunchAboutScreen() {
        onView(withId(R.id.action_settings))
            .perform(click());
        onView(withId(R.id.about_card_view))
            .perform(click())
        intended(hasComponent(ABOUT_ACTIVITY_COMPONENT))
    }

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

    companion object {
        private const val ABOUT_ACTIVITY_COMPONENT =
            "com.k0d4black.theforce.features.about.AboutActivity"
        private const val CHARACTER_DETAIL_ACTIVITY_COMPONENT =
            "com.k0d4black.theforce.features.character_details.CharacterDetailActivity"
    }

}