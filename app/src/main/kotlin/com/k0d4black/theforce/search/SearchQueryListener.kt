package com.k0d4black.theforce.search

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchQueryListener @Inject constructor(val viewModel: CharacterSearchViewModel) :
    SearchView.OnQueryTextListener {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private var searchJob: Job? = null

    var debouncePeriod = 1500L

    override fun onQueryTextSubmit(query: String?): Boolean = false


    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            newText?.let {
                delay(debouncePeriod)
                if (it.isNotBlank())
                    viewModel.executeCharacterSearch(it)
            }
        }
        return true
    }

}