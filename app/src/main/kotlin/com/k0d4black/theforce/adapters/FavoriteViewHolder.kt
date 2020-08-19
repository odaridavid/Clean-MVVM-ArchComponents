package com.k0d4black.theforce.adapters

import android.view.View
import com.k0d4black.theforce.databinding.ItemFavoriteBinding
import com.k0d4black.theforce.models.FavoritePresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class FavoriteViewHolder(
    view: View
) : RecyclerViewHolder<FavoritePresentation>(view) {
    val binding = ItemFavoriteBinding.bind(view)
    override fun bind(position: Int, item: FavoritePresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}