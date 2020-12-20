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
package com.k0d4black.theforce.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.adapters.createFilmsAdapter
import com.k0d4black.theforce.adapters.createSpeciesAdapter
import com.k0d4black.theforce.base.BaseFavoritesFragment
import com.k0d4black.theforce.commons.hide
import com.k0d4black.theforce.commons.remove
import com.k0d4black.theforce.commons.show
import com.k0d4black.theforce.commons.showSnackbar
import com.k0d4black.theforce.databinding.FragmentCharacterDetailBinding
import com.k0d4black.theforce.idlingresource.EspressoIdlingResource
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.layout_films.view.*
import kotlinx.android.synthetic.main.layout_planet.view.*
import kotlinx.android.synthetic.main.layout_specie.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CharacterDetailsFragment : BaseFavoritesFragment(R.layout.fragment_character_detail),
    ICharacterDetailsBinder {

    // region Members

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    private val filmsAdapter = createFilmsAdapter()

    private val speciesAdapter = createSpeciesAdapter()

    private lateinit var binding: FragmentCharacterDetailBinding

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as DashboardActivity).setSupportActionBar(details_toolbar)
        (requireActivity() as DashboardActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_character_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = CharacterDetailsFragmentArgs.fromBundle(requireArguments()).character

        if (character == null) {
            characterDetailViewModel
                .displayCharacterError(R.string.error_loading_character_details)
        } else {
            characterName = character.name
            characterDetailViewModel.initView(character)
            checkIfFavorite()
            observeNetworkChanges(character.url)
            characterDetailViewModel.getCharacterDetails(character.url)
            observeFavoritePresentationCreationFromRemote()
        }

        observeDetailViewState()
    }

    // endregion

    // region BaseFavoritesActivity

    override fun bindFavorite(favoritePresentation: FavoritePresentation) {
        //no-op ,this activity addresses only remote character data
    }

    override val rootViewGroup: ViewGroup
        get() = character_details_layout

    // endregion

    // region Private API

    private fun observeDetailViewState() {
        characterDetailViewModel.detailViewState.observe(viewLifecycleOwner, Observer {
            bindCharacterBasicInfo(it.info)
            bindSpecies(it.specie)
            bindFilms(it.films)
            bindPlanet(it.planet)
            it.error?.let { e ->
                onError(resources.getString(e.message))
            }
            if (it.isComplete) {
                showSnackbar(
                    character_details_layout,
                    getString(R.string.info_loading_complete)
                )
                characterDetailViewModel.createFavoritePresentationFromRemoteCharacter()
            }
        })
    }

    private fun observeFavoritePresentationCreationFromRemote() {
        characterDetailViewModel.remoteToFavoritePresentation
            .observe(viewLifecycleOwner, Observer { favPresentation ->
                favoritePresentation = favPresentation
            })
    }

    private fun onError(message: String) {
        films_layout.films_progress_bar.hide()
        planet_layout.planet_progress_bar.hide()
        specie_layout.species_progress_bar.hide()
        films_layout.films_error_text_view.show()
        planet_layout.planet_error_text_view.show()
        specie_layout.specie_error_text_view.show()
        showSnackbar(character_details_layout, message, isError = true)
    }

    private fun onErrorResolved() {
        films_layout.films_error_text_view.remove()
        planet_layout.planet_error_text_view.remove()
        specie_layout.specie_error_text_view.remove()

        films_layout.films_progress_bar.show()
        planet_layout.planet_progress_bar.show()
        specie_layout.species_progress_bar.show()
    }

    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->
            characterDetailViewModel.detailViewState.value?.let { viewState ->
                if (isConnected && viewState.error != null) {
                    onErrorResolved()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }
        }
    }

    // endregion

    // region ICharacterDetailsBinder

    override fun bindCharacterBasicInfo(character: CharacterPresentation?) {
        requireActivity().actionBar?.title = character?.name ?: ""
        binding.infoLayout.character = character
    }

    override fun bindSpecies(species: List<SpeciePresentation>?) {
        species?.let {
            with(binding.specieLayout) {
                speciesProgressBar.remove()
                if (species.isNotEmpty()) {
                    characterDetailsSpeciesRecyclerView.apply {
                        adapter = speciesAdapter.apply { submitList(species) }
                        EspressoIdlingResource.decrement()
                    }
                } else noSpeciesTextView.show()
            }
        }
    }

    override fun bindFilms(films: List<FilmPresentation>?) {
        films?.let {
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                characterDetailsFilmsRecyclerView.apply {
                    adapter = filmsAdapter.apply { submitList(films) }
                    EspressoIdlingResource.decrement()
                }
            }
        }
    }

    override fun bindPlanet(planet: PlanetPresentation?) {
        planet?.let {
            with(binding.planetLayout) {
                planetProgressBar.remove()
                this.planet = planet
                characterDetailsPlanetNameTextView.show()
                characterDetailsPlanetPopulationTextView.show()
            }
        }
    }

    // endregion
}
