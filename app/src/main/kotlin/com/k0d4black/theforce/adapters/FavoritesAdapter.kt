package com.k0d4black.theforce.adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.ItemFavoriteBinding
import com.k0d4black.theforce.models.FavoritePresentation
import kotlinx.android.synthetic.main.item_favorite.view.*

/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
internal class FavoritesAdapter(
    val onClick: (FavoritePresentation) -> (Unit)
) : ListAdapter<FavoritePresentation, FavoritesAdapter.FavoriteViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return FavoriteViewHolder(ItemFavoriteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritePresentation: FavoritePresentation) {
            binding.favorite = favoritePresentation
            binding.executePendingBindings()
            binding.favCard.setOnClickListener {
                onClick(favoritePresentation)
            }
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<FavoritePresentation>() {
            override fun areItemsTheSame(
                oldItem: FavoritePresentation,
                newItem: FavoritePresentation
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: FavoritePresentation,
                newItem: FavoritePresentation
            ): Boolean {
                return oldItem.characterPresentation.name == newItem.characterPresentation.name &&
                        oldItem.characterPresentation.birthYear == newItem.characterPresentation.birthYear &&
                        oldItem.characterPresentation.heightInCm == newItem.characterPresentation.heightInCm
            }
        }
    }
}