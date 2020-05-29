package com.k0d4black.theforce.features.character_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.models.CharacterPresentation
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterPresentation>(CHARACTER_PARCEL_KEY)

        character?.let { characterInfo ->
            characterDetailViewModel.getCharacterDetails(characterInfo.url)
            renderCharacterInfo(characterInfo)
            observeNetworkChanges(characterInfo.url)
        } ?: characterDetailViewModel
            .displayCharacterError(getString(R.string.error_character_details))

        observeDetailViewState()
    }

    private fun observeDetailViewState() {
        characterDetailViewModel.detailViewState.observe(this, Observer {
            renderSpecies(it)

            renderFilms(it)

            renderPlanetDetails(it)

            renderOnError(it)

            renderOnComplete(it)
        })
    }

    private fun renderCharacterInfo(character: CharacterPresentation) {
        supportActionBar?.title = character.name
        binding.infoLayout.character = character
    }

    private fun renderOnComplete(it: CharacterDetailsViewState) {
        if (it.isComplete)
            showSnackbar(binding.characterDetailsLayout, getString(R.string.info_loading_complete))
    }

    private fun renderSpecies(it: CharacterDetailsViewState) {
        it.specie?.let { species ->
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

    private fun renderOnError(it: CharacterDetailsViewState) {
        it.error?.let { e ->
            displayErrorState(e.message)
        }
    }


    private fun renderFilms(it: CharacterDetailsViewState) {
        it.films?.let { films ->
            with(binding.filmsLayout) {
                filmsProgressBar.remove()
                characterDetailsFilmsRecyclerView.apply {
                    adapter = filmsAdapter.apply { submitList(films) }
                }
            }
        }
    }

    private fun renderPlanetDetails(it: CharacterDetailsViewState) {
        it.planet?.let { planet ->
            with(binding.planetLayout) {
                planetProgressBar.remove()
                this.planet = planet
                characterDetailsPlanetNameTextView.show()
                characterDetailsPlanetPopulationTextView.show()
            }
        }
    }

    private fun displayErrorState(message: String) {
        binding.filmsLayout.filmsProgressBar.hide()
        binding.planetLayout.planetProgressBar.hide()
        binding.specieLayout.speciesProgressBar.hide()
        binding.filmsLayout.filmsErrorTextView.show()
        binding.planetLayout.planetErrorTextView.show()
        binding.specieLayout.specieErrorTextView.show()
        showSnackbar(character_details_layout, message, isError = true)
    }

    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->
            characterDetailViewModel.detailViewState.value?.let { viewState ->
                if (isConnected && viewState.error != null) {
                    binding.filmsLayout.filmsErrorTextView.remove()
                    binding.planetLayout.planetErrorTextView.remove()
                    binding.specieLayout.specieErrorTextView.remove()
                    binding.filmsLayout.filmsProgressBar.show()
                    binding.planetLayout.planetProgressBar.show()
                    binding.specieLayout.speciesProgressBar.show()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }
        }
    }

    private fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->
                block(isConnected)
            })
    }
}
