package com.k0d4black.theforce.feature.characterdetails.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.shared.model.Specie

internal class SpeciesAdapter : ListAdapter<Specie, SpecieViewHolder>(speciesDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SpecieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

private val speciesDiffUtil = object : DiffUtil.ItemCallback<Specie>() {
    override fun areItemsTheSame(oldItem: Specie, newItem: Specie): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Specie, newItem: Specie): Boolean {
        TODO("Not yet implemented")
    }

}