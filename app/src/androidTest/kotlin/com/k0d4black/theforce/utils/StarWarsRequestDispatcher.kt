package com.k0d4black.theforce.utils

import android.content.Context
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

/**
 * Handles Requests from mock web server
 */
class StarWarsRequestDispatcher(val context: Context) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/people?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/character_search.json",
                            context
                        )
                    )
            }
            "/people?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/character_search_no_match.json",
                            context
                        )
                    )
            }
            "/people/$NON_EXISTANT_CHARACTER_ID/" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(
                        getJson(
                            "json/not_found.json",
                            context
                        )
                    )
            }
            "/people/$EXISTING_CHARACTER_ID/" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/character_details.json",
                            context
                        )
                    )
            }
            "/species/$SPECIES_ID/" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/characters_species.json",
                            context
                        )
                    )
            }
            "/films/$FILM_ID/" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/character_films.json",
                            context
                        )
                    )
            }
            "/planets/$PLANET_ID/" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson(
                            "json/character_planet.json",
                            context
                        )
                    )
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}