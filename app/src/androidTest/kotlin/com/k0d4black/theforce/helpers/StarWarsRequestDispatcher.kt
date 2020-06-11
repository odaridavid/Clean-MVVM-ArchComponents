package com.k0d4black.theforce.helpers

import com.k0d4black.theforce.sample.*
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

/**
 * Handles Requests from mock web server
 */
internal class StarWarsRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/people/?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(searchSuccess)
            }
            "/people/?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(searchNoMatch)
            }
            "/people/?search=$ERROR_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(notFound)
            }
            NON_EXISTANT_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(notFound)
            }
            EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterDetails)
            }
            SPECIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterSpecies)
            }
            FILM_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterFilms)
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterPlanet)
            }
            else -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            }
        }
    }

}