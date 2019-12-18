package com.k0d4black.theforce.data.helpers

import com.k0d4black.theforce.data.source.utils.ApiUtils.BASE_URL
import com.k0d4black.theforce.data.api.ApiClient
import com.k0d4black.theforce.data.api.StarWarsApiService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseTest {

    lateinit var mockWebServer: MockWebServer

    lateinit var starWarsApiService: StarWarsApiService

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = StarWarsRequestDispatcher()
        mockWebServer.start()
        BASE_URL = "/"
        starWarsApiService =
            ApiClient().getInstance(
                mockWebServer.url(BASE_URL).toString(),
                StarWarsApiService::class.java
            )
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}