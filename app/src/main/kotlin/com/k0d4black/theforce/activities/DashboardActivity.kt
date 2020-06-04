package com.k0d4black.theforce.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.adapters.SearchResultAdapter
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivityDashboardBinding
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.states.SearchViewState
import com.k0d4black.theforce.viewmodel.DashboardFavoritesViewModel
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class DashboardActivity : BaseActivity() {

    private val characterSearchViewModel by viewModel<DashboardSearchViewModel>()
//    private val favoritesViewModel by viewModel<DashboardFavoritesViewModel>()

    private lateinit var binding: ActivityDashboardBinding

    private val searchResultAdapter: SearchResultAdapter by lazy {
        SearchResultAdapter { character ->
            startActivity<CharacterDetailActivity> {
                putExtra(NavigationUtils.CHARACTER_PARCEL_KEY, character)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        configSupportActionBar()

        observeSearchViewState()
//        observeFavoritesViewState()

        onInitialEditTextClick()

        handleTextChanges()
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

//    private fun observeFavoritesViewState() {
//        favoritesViewModel.favoritesViewState.observe(this, Observer { state ->
//
//        })
//    }

    private fun observeSearchViewState() {
        characterSearchViewModel.searchViewState.observe(this, Observer { state ->
            state.searchResults?.let { searchResults ->
                if (searchResults.isEmpty()) {
                    handleNoSearchResults()
                    return@let
                }
                handleSearchResults(searchResults)
            }

            handleSearchError(state)

            handleSearchLoading(state)
        })
    }

    private fun handleSearchLoading(state: SearchViewState) {
        if (state.isLoading) {
            binding.searchResultsRecyclerView.hide()
            binding.searchResultsProgressBar.show()
        } else {
            binding.searchResultsProgressBar.hide()
            binding.searchResultsRecyclerView.show()
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
            })
            initRecyclerViewWithLineDecoration(this@DashboardActivity)
        }
    }

    private fun handleNoSearchResults() {
        binding.searchResultsRecyclerView.hide()
        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_no_results)
        )
    }

    private fun handleSearchError(state: SearchViewState) {
        state.error?.run {
            showSnackbar(
                binding.searchResultsRecyclerView,
                getString(this.message),
                isError = true
            )
        }
    }

}
