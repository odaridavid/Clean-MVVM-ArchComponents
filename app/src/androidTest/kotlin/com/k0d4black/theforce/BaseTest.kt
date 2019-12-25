package com.k0d4black.theforce

import com.k0d4black.theforce.helpers.StarWarsRequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class BaseTest {

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = StarWarsRequestDispatcher()
            start(8080)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}