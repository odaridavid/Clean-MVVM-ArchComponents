package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseFavoritesActivity
import com.k0d4black.theforce.commons.remove
import com.k0d4black.theforce.commons.show
import com.k0d4black.theforce.databinding.ActivityFavoritesBinding
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.adapters.FilmViewHolder
import com.k0d4black.theforce.adapters.SpecieViewHolder
import com.k0d4black.theforce.adapters.createFilmsAdapter
import com.k0d4black.theforce.adapters.createSpeciesAdapter

internal class FavoriteDetailsActivity : BaseFavoritesActivity(), ICharacterDetailsBinder {

    // region Members

    private lateinit var binding: ActivityFavoritesBinding

    private val filmsAdapter = createFilmsAdapter()

    private val speciesAdapter = createSpeciesAdapter()

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)
        setupToolbar()
        super.onCreate(savedInstanceState)
    }

    // endregion

    // region BaseFavoritesActivity

    override fun bindFavorite(favoritePresentation: FavoritePresentation) {
        bindCharacterBasicInfo(favoritePresentation.characterPresentation)
        bindPlanet(favoritePresentation.planetPresentation)
        bindSpecies(favoritePresentation.speciePresentation)
        bindFilms(favoritePresentation.films)
    }

    override val rootViewGroup: ViewGroup
        get() = binding.favoritesLayout

    // endregion

    // region Private API

    private fun setupToolbar() {
        setSupportActionBar(binding.favoritesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // endregion

    // region ICharacterDetailsBinder

    override fun bindCharacterBasicInfo(character: CharacterPresentation?) {
        supportActionBar?.title = character?.name ?: ""
        binding.infoLayout.character = character
    }

    override fun bindFilms(films: List<FilmPresentation>?) {
        films?.let {
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                characterDetailsFilmsRecyclerView.apply {
                    adapter = filmsAdapter.apply { submitList(films) }
                }
            }
        }
    }

    override fun bindSpecies(species: List<SpeciePresentation>?) {
        species?.let {
            with(binding.specieLayout) {
                speciesProgressBar.remove()
                if (species.isNotEmpty()) {
                    characterDetailsSpeciesRecyclerView.apply {
                        adapter = speciesAdapter.apply { submitList(species) }
                    }
                } else noSpeciesTextView.show()
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
