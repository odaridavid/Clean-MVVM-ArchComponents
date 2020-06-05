package com.k0d4black.theforce

import com.github.odaridavid.data.local.dao.FavoritesDao
import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.helpers.StarWarsRequestDispatcher
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
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}