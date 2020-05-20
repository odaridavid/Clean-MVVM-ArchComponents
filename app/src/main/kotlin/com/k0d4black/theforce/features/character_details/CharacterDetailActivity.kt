package com.k0d4black.theforce.features.character_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.models.CharacterPresentation
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_character_detail.*
import javax.inject.Inject

class CharacterDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels { viewModelFactory }

    lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterPresentation>(CHARACTER_PARCEL_KEY)

        character?.run {
            characterDetailViewModel.getCharacterDetails(this.url)
            displayCharacterDetails(this)
        } ?: characterDetailViewModel.displayCharacterError()

        observeUiState()
        observeCharacterPlanet()
        observeCharacterFilms()
        observeCharacterSpecies()
    }

    private fun observeCharacterSpecies() {
        characterDetailViewModel.characterStarWarsCharacterSpecies.observe(
            this,
            Observer { species ->
                if (species.isNotEmpty()) {
                    binding.characterDetailsSpeciesRecyclerView.apply {
                        adapter = speciesAdapter.apply { submitList(species) }
                        initRecyclerViewWithLineDecoration(this@CharacterDetailActivity)
                    }
                    enableGroup(R.id.character_species_group)
                }
            })
    }

    private fun observeCharacterFilms() {
        characterDetailViewModel.starWarsCharacterFilms.observe(this, Observer { films ->
            binding.characterDetailsFilmsRecyclerView.apply {
                adapter = filmsAdapter.apply { submitList(films) }
                layoutManager =
                    LinearLayoutManager(
                        this@CharacterDetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
            }
            enableGroup(R.id.character_film_group)
        })
    }

    private fun observeCharacterPlanet() {
        characterDetailViewModel.characterPlanet.observe(this, Observer { planet ->
            binding.planet = planet
            enableGroup(R.id.character_planet_group)
        })
    }

    private fun displayCharacterDetails(character: CharacterPresentation) {
        supportActionBar?.title = character.name
        binding.character = character
        enableGroup(R.id.character_details_group)
    }

    private fun observeUiState() {
        characterDetailViewModel.uiState.observe(this, Observer {
            val anchor = character_details_layout
            when (it) {
                is Success<*> -> {
                    showSnackbar(
                        anchor,
                        getString(R.string.info_loading_complete)
                    )
                    binding.loadingCharacterProgressBar.hide()
                }
                is Error -> displayErrorState(it.error)
                is Loading -> showSnackbar(
                    anchor,
                    getString(R.string.info_loading_status)
                )
            }
        })
    }


    private fun displayErrorState(exception: Throwable) {
        binding.loadingCharacterProgressBar.hide()
        binding.loadingErrorTextView.show()
        showSnackbar(
            character_details_layout,
            "${exception.message}"
        )
    }

    //Synthetics upcasting to View , Define type explicitly
    private fun enableGroup(@IdRes groupId: Int) = findViewById<Group>(groupId).animate()
        .alpha(1f)
        .setListener(AnimatorListener(onEnd = {
            findViewById<Group>(groupId).show()
        }))

}
