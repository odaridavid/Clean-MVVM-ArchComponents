package com.k0d4black.theforce.feature.characterdetails.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.shared.model.Film

internal class FilmsAdapter : ListAdapter<Film, FilmViewHolder>(filmAdapterDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

private val filmAdapterDiffUtil = object : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        TODO("Not yet implemented")
    }

}