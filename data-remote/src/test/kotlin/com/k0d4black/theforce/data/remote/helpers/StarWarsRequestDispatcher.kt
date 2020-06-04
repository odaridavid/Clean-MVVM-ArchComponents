package com.k0d4black.theforce.data.remote.helpers

import com.k0d4black.theforce.data.remote.helpers.Constants.EXISTING_CHARACTER_URL
import com.k0d4black.theforce.data.remote.helpers.Constants.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.remote.helpers.Constants.FILM_URL
import com.k0d4black.theforce.data.remote.helpers.Constants.NON_EXISTANT_CHARACTER_URL
import com.k0d4black.theforce.data.remote.helpers.Constants.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.data.remote.helpers.Constants.PLANET_URL
import com.k0d4black.theforce.data.remote.helpers.Constants.SPECIES_URL
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
            "/people?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_search.json"))
            }
            "/people?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson("json/character_search_no_match.json")
                    )
            }
            NON_EXISTANT_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(getJson("json/not_found.json"))
            }
            EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_details.json"))
            }
            SPECIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/characters_species.json"))
            }
            FILM_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_films.json"))
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_planet.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}