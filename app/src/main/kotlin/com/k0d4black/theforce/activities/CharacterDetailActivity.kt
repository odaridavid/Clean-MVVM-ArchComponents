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
import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.states.CharacterDetailsViewState
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class CharacterDetailActivity : BaseActivity() {

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    private lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        setSupportActionBar(binding.detailsToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val character = intent.getParcelableExtra<CharacterPresentation>(NavigationUtils.CHARACTER_PARCEL_KEY)

        character?.let { characterInfo ->
            characterDetailViewModel.getCharacterDetails(characterInfo.url)
            renderCharacterInfo(characterInfo)
            observeNetworkChanges(characterInfo.url)
        } ?: characterDetailViewModel
            .displayCharacterError(R.string.error_loading_character_details)

        observeDetailViewState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_alter_favorites -> {
                //TODO Save and remove to db and update action bar icon as needed
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

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
            displayErrorState(resources.getString(e.message))
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
                    resolveErrorViewState()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }
        }
    }

    private fun resolveErrorViewState() {
        binding.filmsLayout.filmsErrorTextView.remove()
        binding.planetLayout.planetErrorTextView.remove()
        binding.specieLayout.specieErrorTextView.remove()
        binding.filmsLayout.filmsProgressBar.show()
        binding.planetLayout.planetProgressBar.show()
        binding.specieLayout.speciesProgressBar.show()
    }

    private fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->
                block(isConnected)
            })
    }
}
