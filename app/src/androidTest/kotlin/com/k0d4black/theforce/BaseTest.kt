package com.k0d4black.theforce

import androidx.test.espresso.IdlingRegistry
import com.github.odaridavid.data.local.dao.FavoritesDao
import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.helpers.StarWarsRequestDispatcher
import com.k0d4black.theforce.idlingresource.EspressoIdlingResource
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.koin.core.inject
import org.koin.test.KoinTest

open class BaseTest : KoinTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = StarWarsRequestDispatcher()
            start(8080)
        }
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}