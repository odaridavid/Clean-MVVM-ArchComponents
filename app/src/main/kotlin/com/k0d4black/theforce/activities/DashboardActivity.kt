package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.adapters.FavoritesAdapter
import com.k0d4black.theforce.adapters.SearchResultAdapter
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityDashboardBinding
import com.k0d4black.theforce.idlingresource.EspressoIdlingResource
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.DashboardFavoritesViewState
import com.k0d4black.theforce.models.states.DashboardSearchViewState
import com.k0d4black.theforce.viewmodel.DashboardFavoritesViewModel
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO Favorites text view dissappears on transition to end and back
internal class DashboardActivity : BaseActivity() {

    private val characterSearchViewModel by viewModel<DashboardSearchViewModel>()
    private val favoritesViewModel by viewModel<DashboardFavoritesViewModel>()

    private lateinit var binding: ActivityDashboardBinding

    private val searchResultAdapter: SearchResultAdapter by lazy {
        SearchResultAdapter { character ->
            startActivity<CharacterDetailActivity> {
                putExtra(NavigationUtils.CHARACTER_PARCEL_KEY, character)
            }
        }
    }

    private val favoritesAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter { favorite ->
            startActivity<CharacterDetailActivity> {
                putExtra(NavigationUtils.FAVORITE_PARCEL_KEY, favorite)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        configSupportActionBar()

        observeSearchViewState()
        observeFavoritesViewState()

        onInitialEditTextClick()

        handleTextChanges()
    }

    override fun onResume() {
        super.onResume()
        if (binding.dashboardLayout.currentState == binding.dashboardLayout.startState)
            favoritesViewModel.getAllFavorites()
    }

    private fun configSupportActionBar() {
        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun onInitialEditTextClick() {
        binding.searchEditText.setOnFocusChangeListener { _, _ ->
            binding.dashboardLayout.transitionToEnd()
        }
    }

    private fun handleTextChanges() {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text?.let { name ->
                if (name.length >= 2) {
                    binding.dashboardLayout.transitionToEnd()
                    characterSearchViewModel.executeCharacterSearch(name.toString())
                }
            }
        }
    }

    fun handleUpButtonClick(view: View) {
        binding.dashboardLayout.transitionToStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            startActivity<SettingsActivity>()
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun observeFavoritesViewState() {
        favoritesViewModel.dashboardFavoritesViewState.observe(this, Observer { state ->

            handleFavoritesLoading(state)

            state.favorites?.let { favorites ->
                if (favorites.isNotEmpty()) {
                    handleFavorites(favorites)
                } else {
                    binding.noFavoritesTextView.show()
                }
            }

            handleFavoritesError(state)
        })
    }

    private fun observeSearchViewState() {
        characterSearchViewModel.dashboardSearchViewState.observe(this, Observer { state ->

            handleSearchLoading(state)

            state.searchResults?.let { searchResults ->
                if (searchResults.isEmpty()) {
                    handleNoSearchResults()
                    return@let
                }
                handleSearchResults(searchResults)
            }

            handleSearchError(state)

        })
    }

    private fun handleSearchLoading(stateDashboard: DashboardSearchViewState) {
        if (stateDashboard.isLoading) {
            binding.searchResultsRecyclerView.hide()
            binding.searchResultsProgressBar.show()
        } else {
            binding.searchResultsProgressBar.hide()
            binding.searchResultsRecyclerView.show()
        }
    }

    private fun handleFavoritesLoading(stateDashboard: DashboardFavoritesViewState) {
        if (stateDashboard.isLoading) {
            binding.favoritesProgressBar.show()
        } else {
            binding.favoritesProgressBar.hide()
        }
    }

    private fun handleSearchResults(searchResults: List<CharacterPresentation>) {
        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_search_done)
        )
        binding.searchResultsRecyclerView.apply {
            adapter = ScaleInAnimationAdapter(searchResultAdapter.apply {
                submitList(searchResults)
                EspressoIdlingResource.decrement()
            })
            initRecyclerViewWithLineDecoration(this@DashboardActivity)
        }
    }

    private fun handleFavorites(favorites: List<FavoritePresentation>) {
        binding.noFavoritesTextView.hide()
        binding.favoritesRecyclerView.show()
        binding.favoritesRecyclerView.apply {
            adapter = ScaleInAnimationAdapter(favoritesAdapter.apply {
                submitList(favorites)
                EspressoIdlingResource.decrement()
            })
        }
    }

    private fun handleNoSearchResults() {
        binding.searchResultsRecyclerView.hide()
        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_no_results)
        )
    }

    private fun handleSearchError(stateDashboard: DashboardSearchViewState) {
        stateDashboard.error?.run {
            showSnackbar(
                binding.searchResultsRecyclerView,
                getString(this.message),
                isError = true
            )
        }
    }

    private fun handleFavoritesError(stateDashboard: DashboardFavoritesViewState) {
        stateDashboard.error?.run {
            showSnackbar(
                binding.favoritesRecyclerView,
                getString(this.message),
                isError = true
            )
        }
    }
}
