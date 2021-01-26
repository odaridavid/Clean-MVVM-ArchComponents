package com.k0d4black.theforce.feature.charactersearchresults.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.shared.model.Character

internal class CharacterSearchResultsAdapter :
    ListAdapter<Character, CharacterSearchResultViewHolder>(characterSearchResultsDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterSearchResultViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CharacterSearchResultViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

private val characterSearchResultsDiffUtil = object : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }
}