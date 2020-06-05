package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.adapters.FilmsAdapter
import com.k0d4black.theforce.adapters.SpeciesAdapter
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.models.states.CharacterDetailsViewState
import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO Disable Saving favs for remote till everything is done
//TODO Some Characters not bloody saving
internal class CharacterDetailActivity : BaseActivity() {

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    private lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    private var isFavorite = false

    private var characterName = ""

    private var favoritePresentation: FavoritePresentation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val character =
            intent.getParcelableExtra<CharacterPresentation>(NavigationUtils.CHARACTER_PARCEL_KEY)

        val favorite =
            intent.getParcelableExtra<FavoritePresentation>(NavigationUtils.FAVORITE_PARCEL_KEY)

        if (character == null && favorite == null) {
            characterDetailViewModel
                .displayCharacterError(R.string.error_loading_character_details)
        }

        character?.let { characterPresentation ->
            characterName = characterPresentation.name
            characterDetailViewModel.getCharacterDetails(characterPresentation.url)
            onInitCheckIfFavorite()
            handleCharacterInfo(characterPresentation)
            observeNetworkChanges(characterPresentation.url)
        }

        favorite?.let { favoritePresentation ->
            bindFavorite(favoritePresentation)
            characterName = favoritePresentation.characterPresentation.name
            this.favoritePresentation = favoritePresentation
            onInitCheckIfFavorite()
        }

        observeDetailViewState()
        observeDetailFavoriteViewState()
    }

    private fun onInitCheckIfFavorite() {
        characterDetailViewModel.getFavorite(characterName)
    }

    private fun bindFavorite(favoritePresentation: FavoritePresentation) {
        handleCharacterInfo(favoritePresentation.characterPresentation)
        handleSpecies(listOf(favoritePresentation.speciePresentation))
        handleFilms(favoritePresentation.films)
        handlePlanet(favoritePresentation.planetPresentation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.getItem(0)
        if (isFavorite)
            menuItem?.setIcon(R.drawable.ic_fav_on)
        else
            menuItem?.setIcon(R.drawable.ic_fav_off)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_alter_favorites -> {
                isFavorite =
                    if (isFavorite) {
                        removeFromFavorites()
                        false
                    } else {
                        favoritePresentation?.let { favorite ->
                            addToFavorites(favorite)
                            true
                        } ?: false
                    }
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorites(favorite: FavoritePresentation) {
        characterDetailViewModel.saveFavorite(favorite)
        showSnackbar(
            binding.characterDetailsLayout,
            getString(R.string.info_added_to_favs)
        )
    }

    private fun removeFromFavorites() {
        characterDetailViewModel.deleteFavorite(characterName)
        showSnackbar(
            binding.characterDetailsLayout,
            getString(R.string.info_removed_from_favs)
        )
    }

    private fun observeDetailViewState() {
        characterDetailViewModel.detailViewState.observe(this, Observer {
            handleSpecies(it.specie)
            handleFilms(it.films)
            handlePlanet(it.planet)
            it.error?.let { e ->
                handleOnError(resources.getString(e.message))
            }
            if (it.isComplete) {
                showSnackbar(
                    binding.characterDetailsLayout,
                    getString(R.string.info_loading_complete)
                )
                favoritePresentation = createFavoriteFromRemoteCharacter(it)
            }
        })
    }

    private fun observeDetailFavoriteViewState() {
        characterDetailViewModel.detailFavoriteViewState.observe(this, Observer {
            isFavorite = it.isFavorite
            invalidateOptionsMenu()
            it.error?.let { e ->
                showSnackbar(binding.characterDetailsLayout, getString(e.message))
            }
        })
    }

    private fun createFavoriteFromRemoteCharacter(state: CharacterDetailsViewState): FavoritePresentation {
        val characterPresentation = CharacterPresentation(
            characterName,
            binding.infoLayout.character?.birthYear ?: "Unknown",
            binding.infoLayout.character?.heightInCm ?: "Unknown",
            binding.infoLayout.character?.heightInInches ?: "Unknown",
            ""
        )
        val planetPresentation =
            PlanetPresentation(state.planet?.name ?: "Unknown", state.planet?.population ?: 0L)
        val speciePresentation = SpeciePresentation(
            state.specie?.get(0)?.name ?: "Unknown",
            state.specie?.get(0)?.language ?: "Unknown"
        )
        return FavoritePresentation(
            characterPresentation = characterPresentation,
            planetPresentation = planetPresentation,
            speciePresentation = speciePresentation,
            films = state.films ?: emptyList()
        )
    }

    private fun handleCharacterInfo(character: CharacterPresentation) {
        supportActionBar?.title = character.name
        binding.infoLayout.character = character
    }

    private fun handleSpecies(species: List<SpeciePresentation>?) {
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

    private fun handleFilms(films: List<FilmPresentation>?) {
        films?.let {
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                characterDetailsFilmsRecyclerView.apply {
                    adapter = filmsAdapter.apply { submitList(films) }
                }
            }
        }
    }

    private fun handlePlanet(planet: PlanetPresentation?) {
        planet?.let {
            with(binding.planetLayout) {
                planetProgressBar.remove()
                this.planet = planet
                characterDetailsPlanetNameTextView.show()
                characterDetailsPlanetPopulationTextView.show()
            }
        }
    }

    private fun handleOnError(message: String) {
        binding.filmsLayout.filmsProgressBar.hide()
        binding.planetLayout.planetProgressBar.hide()
        binding.specieLayout.speciesProgressBar.hide()
        binding.filmsLayout.filmsErrorTextView.show()
        binding.planetLayout.planetErrorTextView.show()
        binding.specieLayout.specieErrorTextView.show()
        showSnackbar(binding.characterDetailsLayout, message, isError = true)
    }

    private fun resolveError() {
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
                    resolveError()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }
        }
    }

}
