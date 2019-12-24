package com.k0d4black.theforce.data


import com.k0d4black.theforce.data.helpers.StarWarsRequestDispatcher
import com.k0d4black.theforce.data.api.HttpClient
import com.k0d4black.theforce.data.api.LoggingInterceptor
import com.k0d4black.theforce.data.api.StarWarsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


open class BaseTest {

    lateinit var mockWebServer: MockWebServer

    lateinit var starWarsApiService: StarWarsApiService

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher =
            StarWarsRequestDispatcher()
        mockWebServer.start()
        loggingInterceptor = LoggingInterceptor.create()
        okHttpClient = HttpClient.setupOkhttpClient(loggingInterceptor)

        starWarsApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StarWarsApiService::class.java)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}