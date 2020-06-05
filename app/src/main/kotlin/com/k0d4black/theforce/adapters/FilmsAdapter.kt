package com.k0d4black.theforce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.ItemFilmBinding
import com.k0d4black.theforce.models.FilmPresentation

internal class FilmsAdapter : ListAdapter<FilmPresentation, FilmsAdapter.FilmViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return FilmViewHolder(ItemFilmBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class FilmViewHolder(
        private val binding: ItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(filmPresentation: FilmPresentation) {
            binding.film = filmPresentation
            binding.executePendingBindings()
        }
    }

    companion object {
        val DiffUtil =
            object : DiffUtil.ItemCallback<FilmPresentation>() {
                override fun areItemsTheSame(
                    oldItem: FilmPresentation,
                    newItem: FilmPresentation
                ): Boolean = oldItem == newItem


                override fun areContentsTheSame(
                    oldItem: FilmPresentation,
                    newItem: FilmPresentation
                ): Boolean = oldItem.title == newItem.title
            }
    }
}