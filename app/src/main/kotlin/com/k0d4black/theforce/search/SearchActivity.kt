package com.k0d4black.theforce.search

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.k0d4black.theforce.R
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Loading
import com.k0d4black.theforce.domain.utils.Success
import com.k0d4black.theforce.hide
import com.k0d4black.theforce.search.models.SearchedCharacterPresentationModel
import com.k0d4black.theforce.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val characterSearchViewModel: CharacterSearchViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeSearchResults()
    }

    private fun observeSearchResults() {
        characterSearchViewModel.searchResults.observe(this, Observer {
            when (it) {
                is Success -> displayDataLoadedState(it.data)
                is Error -> displayErrorState(it.exception)
                is Loading -> displayLoadingState()
            }
        })
    }

    //TODO Animations
    private fun displayLoadingState() {
        loading_search_results_progress_bar.show()
        search_tip_text_view.hide()
    }

    private fun displayDataLoadedState(data: List<SearchedCharacterPresentationModel>) {
        loading_search_results_progress_bar.hide()
        if (data.isNotEmpty()) {
            val linearLayoutManager = LinearLayoutManager(this)
            search_results_recycler_view.apply {
                adapter = searchResultAdapter.apply { submitList(data) }
                layoutManager = linearLayoutManager
                addItemDecoration(
                    DividerItemDecoration(this@SearchActivity, linearLayoutManager.orientation)
                )
            }
        } else displayEmptyDataState()
    }

    private fun displayEmptyDataState() {
        search_tip_text_view.show()
        Snackbar.make(
            search_results_recycler_view,
            getString(R.string.info_no_results),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun displayErrorState(e: Exception) {
        loading_search_results_progress_bar.hide()
        search_tip_text_view.show()
        Snackbar.make(search_results_recycler_view, "$e", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(SearchQueryListener(characterSearchViewModel))
        return super.onCreateOptionsMenu(menu)
    }

}
