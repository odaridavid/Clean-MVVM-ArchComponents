package com.k0d4black.theforce.feature.character.details

import android.view.View
import com.k0d4black.theforce.databinding.ItemFilmBinding
import com.k0d4black.theforce.models.FilmPresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class FilmViewHolder(
    view: View
) : RecyclerViewHolder<FilmPresentation>(view) {
    val binding = ItemFilmBinding.bind(view)

    override fun bind(position: Int, item: FilmPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}