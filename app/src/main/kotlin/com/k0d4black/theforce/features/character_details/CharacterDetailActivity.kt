package com.k0d4black.theforce.features.character_details

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.models.CharacterPresentation
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO Set Intent Data to be part of the view state
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

            renderOnLoading(it)

            renderOnError(it)

            renderOnComplete(it)
        })
    }

    private fun renderCharacterInfo(character: CharacterPresentation) {
        supportActionBar?.title = character.name
        binding.infoLayout.character = character
        enableGroup(R.id.character_details_group)
    }

    private fun renderOnComplete(it: CharacterDetailsViewState) {
        if (it.isComplete)
            showSnackbar(binding.characterDetailsLayout, getString(R.string.info_loading_complete))
    }

    private fun renderSpecies(it: CharacterDetailsViewState) {
        it.specie?.let { species ->
            if (species.isNotEmpty()) {
                binding.specieLayout.noSpeciesTextView.hide()
                binding.specieLayout.characterDetailsSpeciesRecyclerView.apply {
                    adapter = speciesAdapter.apply { submitList(species) }
                }
            } else binding.specieLayout.noSpeciesTextView.show()
            enableGroup(R.id.character_species_group)
        }
    }

    private fun renderOnError(it: CharacterDetailsViewState) {
        it.error?.let { e ->
            displayErrorState(e.message)
        } ?: binding.loadingErrorTextView.hide()
    }

    private fun renderFilms(it: CharacterDetailsViewState) {
        it.films?.let { films ->
            binding.filmsLayout.characterDetailsFilmsRecyclerView.apply {
                adapter = filmsAdapter.apply { submitList(films) }
            }
            enableGroup(R.id.character_film_group)
        }
    }

    private fun renderPlanetDetails(it: CharacterDetailsViewState) {
        it.planet?.let { planet ->
            binding.planetLayout.planet = planet
            enableGroup(R.id.character_planet_group)
        }
    }

    private fun renderOnLoading(it: CharacterDetailsViewState) {
        if (it.isLoading)
            binding.loadingCharacterProgressBar.show()
        else
            binding.loadingCharacterProgressBar.hide()
    }


    private fun displayErrorState(message: String) {
        binding.loadingErrorTextView.show()
        showSnackbar(character_details_layout, message, isError = true)
    }

    private fun enableGroup(@IdRes groupId: Int) {
        findViewById<Group>(groupId)
            .animate()
            .alpha(1f)
            .setListener(AnimatorListener(onEnd = {
                findViewById<Group>(groupId).show()
            }))
    }

    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->
            characterDetailViewModel.detailViewState.value?.let { viewState ->
                if (isConnected && viewState.error != null) {
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
