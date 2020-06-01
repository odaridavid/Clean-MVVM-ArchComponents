package com.k0d4black.theforce.features.character_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.ItemSpecieBinding
import com.k0d4black.theforce.models.SpeciePresentation

internal class SpeciesAdapter : ListAdapter<SpeciePresentation, SpeciesAdapter.SpecieViewHolder>(
    SearchedCharacterDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return SpecieViewHolder(ItemSpecieBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SpecieViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class SpecieViewHolder(private val binding: ItemSpecieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelStarWarsCharacter: SpeciePresentation) {
            binding.species = modelStarWarsCharacter
            binding.executePendingBindings()
        }
    }

    companion object {
        val SearchedCharacterDiffUtil =
            object : DiffUtil.ItemCallback<SpeciePresentation>() {
                override fun areItemsTheSame(
                    oldItem: SpeciePresentation,
                    newItem: SpeciePresentation
                ): Boolean = oldItem == newItem


                override fun areContentsTheSame(
                    oldItem: SpeciePresentation,
                    newItem: SpeciePresentation
                ): Boolean = oldItem.name == newItem.name
            }
    }
}