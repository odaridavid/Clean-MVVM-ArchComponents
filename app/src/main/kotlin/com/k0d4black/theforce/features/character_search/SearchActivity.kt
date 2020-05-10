package com.k0d4black.theforce.features.character_search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.k0d4black.theforce.R
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import com.k0d4black.theforce.features.settings.SettingsActivity
import com.k0d4black.theforce.models.StarWarsCharacterUiModel
import dagger.android.AndroidInjection
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject


class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val characterSearchViewModel: CharacterSearchViewModel by viewModels { viewModelFactory }

    private val searchResultAdapter: SearchResultAdapter by lazy {
        SearchResultAdapter { character ->
            Intent(this, CharacterDetailActivity::class.java).apply {
                putExtra(CHARACTER_PARCEL_KEY, character)
            }.also { startActivity(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        observeUiState()
    }

    @Suppress("UNCHECKED_CAST")
    private fun observeUiState() {
        characterSearchViewModel.uiState.observe(this, Observer {
            when (it) {
                is Success<*> -> {
                    val data = it.data as List<StarWarsCharacterUiModel>
                    showSnackbar(search_results_recycler_view, getString(R.string.info_search_done))
                    displaySearchResults(data)
                }
                is Error -> displayErrorState(it.error)
                is Loading -> displayLoadingState()
            }
        })
    }

    private fun displayLoadingState() {
        search_tip_text_view.animate()
            .alpha(0f)
            .setListener(AnimatorListener(onEnd = {
                search_tip_text_view.hide()
                loading_search_results_progress_bar.show()
            }))
    }

    private fun displaySearchResults(searchResultStarWars: List<StarWarsCharacterUiModel>) {
        loading_search_results_progress_bar.animate()
            .alpha(0f)
            .setListener(AnimatorListener(onEnd = { loading_search_results_progress_bar.hide() }))

        if (searchResultStarWars.isNotEmpty()) {

            if (search_tip_text_view.isVisible) search_tip_text_view.hide()

            search_results_recycler_view.apply {
                adapter = ScaleInAnimationAdapter(searchResultAdapter.apply {
                    submitList(searchResultStarWars)
                })
                initRecyclerViewWithLineDecoration(this@SearchActivity)
            }

            search_results_recycler_view.show()

        } else displayNoSearchResults()
    }

    private fun displayNoSearchResults() {
        showSearchTip()
        search_results_recycler_view.hide()
        showSnackbar(
            search_results_recycler_view,
            getString(R.string.info_no_results)
        )
    }

    private fun displayErrorState(error: Throwable) {
        search_results_recycler_view.hide()
        loading_search_results_progress_bar.animate().alpha(0f)
            .setListener(AnimatorListener(onEnd = { loading_search_results_progress_bar.hide() }))
        showSearchTip()
        showSnackbar(search_results_recycler_view, "${error.message}")
    }

    private fun showSearchTip() {
        search_tip_text_view.animate().alpha(1f)
            .setListener(AnimatorListener(onEnd = { search_tip_text_view.show() }))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(
            SearchQueryListener(characterSearchViewModel)
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
            true
        } else super.onOptionsItemSelected(item)
    }

}
