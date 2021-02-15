package com.k0d4black.theforce.feature.charactersearchresults.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.feature.charactersearchresults.R
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation

internal class CharacterSearchResultsAdapter :
    ListAdapter<CharacterSearchResultPresentation, CharacterSearchResultViewHolder>(
        characterSearchResultsDiffUtil
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterSearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return CharacterSearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterSearchResultViewHolder, position: Int) {
        holder.bind(characterSearchResultPresentation = getItem(position))
    }
}

private val characterSearchResultsDiffUtil =
    object : DiffUtil.ItemCallback<CharacterSearchResultPresentation>() {

        override fun areItemsTheSame(
            oldItem: CharacterSearchResultPresentation,
            newItem: CharacterSearchResultPresentation
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CharacterSearchResultPresentation,
            newItem: CharacterSearchResultPresentation
        ): Boolean = oldItem.name == newItem.name
    }
