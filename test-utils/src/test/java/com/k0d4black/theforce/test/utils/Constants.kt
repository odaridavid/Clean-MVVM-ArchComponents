/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.test.utils

internal object Constants {
    //Maintains url path parameters for consistency across tests
    const val SPECIES_URL = "/api/species/1/"
    const val FILM_URL = "/api/films/1/"
    const val PLANET_URL = "/api/planets/1/"
    const val EXISTING_CHARACTER_URL = "/api/people/1/"
    const val NON_EXISTANT_CHARACTER_URL = "/api/people/2000/"
    const val EXISTING_SEARCH_PARAMS = "Darth"
    const val NON_EXISTENT_SEARCH_PARAMS = "Zipa"
}