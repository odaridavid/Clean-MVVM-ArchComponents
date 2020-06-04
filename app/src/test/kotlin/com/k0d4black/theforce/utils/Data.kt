/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.utils

import com.k0d4black.theforce.domain.models.*


object Data {
    val favorite = Favorite(
        1,
        "Hans",
        "12 BBY",
        "123",
        "planet",
        "100000",
        "specie",
        "language",
        listOf(Film("title", "crawl"))
    )
    val favorites = mutableListOf<Favorite>()
    val films = listOf(Film(title = "title", openingCrawl = "opening crawl"))
    val planet = Planet(name = "name", population = "100000")
    val species = listOf(Specie(name = "name", language = "language"))
    val characters = listOf(
        Character(
            "Darth Vader",
            "12BBY",
            "123",
            "/api/species/2/"
        )
    )

    const val CHARACTER_URL = "/api/people/1/"
    const val SEARCH_PARAM = "Darth"

}