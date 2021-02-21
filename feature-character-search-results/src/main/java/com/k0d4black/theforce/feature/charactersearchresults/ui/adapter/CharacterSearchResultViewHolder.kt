package com.k0d4black.theforce.feature.charactersearchresults.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.feature.charactersearchresults.databinding.ItemSearchBinding
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation

class CharacterSearchResultViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(characterSearchResultPresentation: CharacterSearchResultPresentation) {
        with(binding) {
            characterBirthYearTextView.text = characterSearchResultPresentation.birthYear
            characterNameTextView.text = characterSearchResultPresentation.name
        }
    }
}
