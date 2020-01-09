package com.k0d4black.theforce.features.character_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.FilmItemLayoutBinding
import com.k0d4black.theforce.models.CharacterFilmPresentationModel

class FilmsAdapter : ListAdapter<CharacterFilmPresentationModel, FilmsAdapter.FilmViewHolder>(
    SearchedCharacterDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return FilmViewHolder(FilmItemLayoutBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class FilmViewHolder(private val binding: FilmItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelCharacter: CharacterFilmPresentationModel) {
            binding.film = modelCharacter
            binding.executePendingBindings()
        }
    }

    companion object {
        val SearchedCharacterDiffUtil =
            object : DiffUtil.ItemCallback<CharacterFilmPresentationModel>() {
                override fun areItemsTheSame(
                    oldItem: CharacterFilmPresentationModel,
                    newItem: CharacterFilmPresentationModel
                ): Boolean = oldItem.title == newItem.title


                override fun areContentsTheSame(
                    oldItem: CharacterFilmPresentationModel,
                    newItem: CharacterFilmPresentationModel
                ): Boolean = oldItem == newItem
            }
    }
}