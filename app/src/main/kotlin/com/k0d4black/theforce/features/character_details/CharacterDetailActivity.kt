package com.k0d4black.theforce.features.character_details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.k0d4black.theforce.*
import com.k0d4black.theforce.databinding.ActivityCharacterDetailBinding
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Loading
import com.k0d4black.theforce.domain.utils.Success
import com.k0d4black.theforce.models.CharacterDetailsPresentationModel
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

        val characterId = intent.getIntExtra(CHARACTER_ID_KEY, -1)

        characterDetailViewModel.getCharacterDetails(characterId)

        observeCharacterDetailsResult()
    }

    private fun observeCharacterDetailsResult() {
        characterDetailViewModel.characterDetail.observe(this, Observer {
            when (it) {
                is Success -> displayDataLoadedState(it.data)
                is Error -> displayErrorState(it.exception)
                is Loading -> displayLoadingState()
            }
        })
    }

    private fun displayLoadingState() {
        showSnackbar(character_details_layout, getString(R.string.info_loading_status))
    }

    private fun displayErrorState(exception: Exception) {
        binding.loadingCharacterProgressBar.hide()
        showSnackbar(character_details_layout, "${exception.message}")
    }

    private fun displayDataLoadedState(character: CharacterDetailsPresentationModel) {
        supportActionBar?.title = character.name
        binding.character = character

        binding.loadingCharacterProgressBar.hide()

        //Synthetics upcasting to View , Define type explicitly
        val group = findViewById<Group>(R.id.data_group)
        group.visibility = View.VISIBLE

        binding.characterDetailsFilmsRecyclerView.apply {
            adapter = filmsAdapter.apply { submitList(character.films) }
            layoutManager =
                LinearLayoutManager(
                    this@CharacterDetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        binding.characterDetailsSpeciesRecyclerView.apply {
            adapter = speciesAdapter.apply { submitList(character.species) }
            initRecyclerViewWithLineDecoration(this@CharacterDetailActivity)
        }
    }

}
