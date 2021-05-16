package com.k0d4black.theforce.feature.favoritecharacters.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.shared.favorites.FavoriteCharacter

internal class FavoriteCharactersAdapter :
    ListAdapter<FavoriteCharacter, FavoriteCharacterViewHolder>(favoriteCharactersDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharacterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

private val favoriteCharactersDiffUtil = object : DiffUtil.ItemCallback<FavoriteCharacter>() {

    override fun areItemsTheSame(oldItem: FavoriteCharacter, newItem: FavoriteCharacter): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(
        oldItem: FavoriteCharacter,
        newItem: FavoriteCharacter
    ): Boolean {
        TODO("Not yet implemented")
    }
}
