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
package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseFavoritesActivity
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.idlingresource.EspressoIdlingResource
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.adapters.createFilmsAdapter
import com.k0d4black.theforce.adapters.createSpeciesAdapter
import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CharacterDetailsActivity : BaseFavoritesActivity(), ICharacterDetailsBinder {

    // region Members

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    private lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter = createFilmsAdapter()

    private val speciesAdapter = createSpeciesAdapter()

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val character =
            intent.getParcelableExtra<CharacterPresentation>(NavigationUtils.CHARACTER_PARCEL_KEY)

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
        get() = binding.characterDetailsLayout

    // endregion

    // region Private API

    private fun observeDetailViewState() {
        characterDetailViewModel.detailViewState.observe(this, Observer {
            bindCharacterBasicInfo(it.info)
            bindSpecies(it.specie)
            bindFilms(it.films)
            bindPlanet(it.planet)
            it.error?.let { e ->
                onError(resources.getString(e.message))
            }
            if (it.isComplete) {
                showSnackbar(
                    binding.characterDetailsLayout,
                    getString(R.string.info_loading_complete)
                )
                characterDetailViewModel.createFavoritePresentationFromRemoteCharacter()
            }
        })
    }

    private fun observeFavoritePresentationCreationFromRemote() {
        characterDetailViewModel.remoteToFavoritePresentation
            .observe(this, Observer { favPresentation ->
                favoritePresentation = favPresentation
            })
    }

    private fun onError(message: String) {
        binding.filmsLayout.filmsProgressBar.hide()
        binding.planetLayout.planetProgressBar.hide()
        binding.specieLayout.speciesProgressBar.hide()
        binding.filmsLayout.filmsErrorTextView.show()
        binding.planetLayout.planetErrorTextView.show()
        binding.specieLayout.specieErrorTextView.show()
        showSnackbar(binding.characterDetailsLayout, message, isError = true)
    }

    private fun onErrorResolved() {
        binding.filmsLayout.filmsErrorTextView.remove()
        binding.planetLayout.planetErrorTextView.remove()
        binding.specieLayout.specieErrorTextView.remove()
        binding.filmsLayout.filmsProgressBar.show()
        binding.planetLayout.planetProgressBar.show()
        binding.specieLayout.speciesProgressBar.show()
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
        supportActionBar?.title = character?.name ?: ""
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
