package com.k0d4black.theforce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.ItemSpecieBinding
import com.k0d4black.theforce.models.SpeciePresentation

internal class SpeciesAdapter :
    ListAdapter<SpeciePresentation, SpeciesAdapter.SpecieViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return SpecieViewHolder(ItemSpecieBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SpecieViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class SpecieViewHolder(
        private val binding: ItemSpecieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(speciePresentation: SpeciePresentation) {
            binding.species = speciePresentation
            binding.executePendingBindings()
        }
    }

    companion object {
        val DiffUtil =
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