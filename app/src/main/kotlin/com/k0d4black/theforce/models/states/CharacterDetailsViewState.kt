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
package com.k0d4black.theforce.models.states

import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation

internal data class CharacterDetailsViewState(
    val isComplete: Boolean,
    val error: Error?,
    val planet: PlanetPresentation?,
    val films: List<FilmPresentation>?,
    val specie: List<SpeciePresentation>?,
    val info: CharacterPresentation?
)