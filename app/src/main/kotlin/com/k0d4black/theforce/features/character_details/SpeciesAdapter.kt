package com.k0d4black.theforce.features.character_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.SpecieItemLayoutBinding
import com.k0d4black.theforce.models.CharacterSpeciesPresentationModel

class SpeciesAdapter : ListAdapter<CharacterSpeciesPresentationModel, SpeciesAdapter.SpecieViewHolder>(
    SearchedCharacterDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return SpecieViewHolder(SpecieItemLayoutBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SpecieViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class SpecieViewHolder(private val binding: SpecieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelCharacter: CharacterSpeciesPresentationModel) {
            binding.species = modelCharacter
            binding.executePendingBindings()
        }
    }

    companion object {
        val SearchedCharacterDiffUtil =
            object : DiffUtil.ItemCallback<CharacterSpeciesPresentationModel>() {
                override fun areItemsTheSame(
                    oldItem: CharacterSpeciesPresentationModel,
                    newItem: CharacterSpeciesPresentationModel
                ): Boolean = oldItem.name == newItem.name


                override fun areContentsTheSame(
                    oldItem: CharacterSpeciesPresentationModel,
                    newItem: CharacterSpeciesPresentationModel
                ): Boolean = oldItem == newItem
            }
    }
}