package com.k0d4black.theforce.feature.charactersearchresults.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.feature.charactersearchresults.databinding.ItemSearchBinding
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation

internal class CharacterSearchResultsAdapter :
    ListAdapter<CharacterSearchResultPresentation, CharacterSearchResultViewHolder>(
        characterSearchResultsDiffUtil
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterSearchResultViewHolder =
        CharacterSearchResultViewHolder(
            binding = ItemSearchBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

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
