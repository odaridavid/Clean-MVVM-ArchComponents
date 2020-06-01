package com.k0d4black.theforce.features

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.k0d4black.theforce.R
import com.k0d4black.theforce.base.BaseActivity
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.databinding.ActivitySearchBinding
import com.k0d4black.theforce.features.character_details.CharacterDetailActivity
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import com.k0d4black.theforce.features.character_search.SearchResultAdapter
import com.k0d4black.theforce.features.character_search.SearchResultViewState
import com.k0d4black.theforce.features.settings.SettingsActivity
import com.k0d4black.theforce.models.CharacterPresentation
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class DashboardActivity : BaseActivity() {

    private val characterSearchViewModel by viewModel<CharacterSearchViewModel>()

    private lateinit var binding: ActivitySearchBinding

    private val searchResultAdapter: SearchResultAdapter by lazy {
        SearchResultAdapter { character ->
            startActivity<CharacterDetailActivity> {
                putExtra(NavigationUtils.CHARACTER_PARCEL_KEY, character)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        observeSearchViewState()

        binding.searchEditText.setOnFocusChangeListener { _, _ ->
            binding.dashboardLayout.transitionToEnd()
        }
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text?.let { name ->
                if (name.length >= 2) {
                    binding.dashboardLayout.transitionToEnd()
                    characterSearchViewModel.executeCharacterSearch(name.toString())
                }
            }
        }
    }

    private fun observeSearchViewState() {
        characterSearchViewModel.searchViewState.observe(this, Observer { state ->

            renderSearchResults(state)

            renderError(state)

            renderLoading(state)
        })
    }

    private fun renderLoading(state: SearchResultViewState) {
        if (state.isLoading) {
            binding.searchResultsRecyclerView.hide()
            binding.searchResultsProgressBar.show()
        } else {
            binding.searchResultsProgressBar.hide()
            binding.searchResultsRecyclerView.show()
        }
    }

    private fun renderSearchResults(state: SearchResultViewState) {
        state.searchResults?.run {
            if (state.searchResults.isEmpty()) {
                displayNoSearchResults()
                return
            }
            displaySearchResults(state.searchResults)
        }
    }

    private fun displaySearchResults(searchResults: List<CharacterPresentation>) {
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

    private fun displayNoSearchResults() {
        binding.searchResultsRecyclerView.hide()
        showSnackbar(
            binding.searchResultsRecyclerView,
            getString(R.string.info_no_results)
        )
    }

    private fun renderError(state: SearchResultViewState) {
        state.error?.run {
            showSnackbar(binding.searchResultsRecyclerView, getString(this.message), isError = true)
        }
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

}
