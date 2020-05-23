package com.k0d4black.theforce.features.character_search

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.*

class SearchQueryListener(private val viewModel: CharacterSearchViewModel) :
    SearchView.OnQueryTextListener {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private var searchJob: Job? = null

    private var debouncePeriod = 400L

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