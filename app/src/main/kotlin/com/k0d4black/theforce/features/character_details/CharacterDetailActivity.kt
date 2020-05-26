package com.k0d4black.theforce.features.character_details

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.models.CharacterPresentation
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
//TODO Add Swipe to refresh for error scenarios
class CharacterDetailActivity : AppCompatActivity() {

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()

    lateinit var binding: ActivityCharacterDetailBinding

    private val filmsAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<CharacterPresentation>(CHARACTER_PARCEL_KEY)

        character?.run {
            characterDetailViewModel.getCharacterDetails(this.url)
            bindCharacterIntentExtras(this)
        } ?: characterDetailViewModel.displayCharacterError()

        observeUiState()
    }

    private fun bindCharacterIntentExtras(character: CharacterPresentation) {
        supportActionBar?.title = character.name
        binding.character = character
        enableGroup(R.id.character_details_group)
    }

    private fun observeUiState() {
        characterDetailViewModel.detailViewState.observe(this, Observer {
            //Species
            it.specie?.let { species ->
                binding.characterDetailsSpeciesRecyclerView.apply {
                    adapter = speciesAdapter.apply { submitList(species) }
                    initRecyclerViewWithLineDecoration(this@CharacterDetailActivity)
                }
                enableGroup(R.id.character_species_group)
            }
            //Films
            it.films?.let { films ->
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
            }
            //Planet
            it.planet?.let { planet ->
                binding.planet = planet
                enableGroup(R.id.character_planet_group)
            }
            //Loading
            if (it.isLoading) {
                binding.loadingCharacterProgressBar.show()
            } else {
                binding.loadingCharacterProgressBar.hide()
            }

            it.error?.let { e ->
                displayErrorState(e.message)
            }
        })
    }


    private fun displayErrorState(message: String) {
        binding.loadingErrorTextView.show()
        showSnackbar(character_details_layout, message)
    }

    private fun enableGroup(@IdRes groupId: Int) {
        findViewById<Group>(groupId)
            .animate()
            .alpha(1f)
            .setListener(AnimatorListener(onEnd = {
                findViewById<Group>(groupId).show()
            }))
    }

}
