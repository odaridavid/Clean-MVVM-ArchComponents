package com.k0d4black.theforce.feature.character.details

import android.view.View
import com.k0d4black.theforce.databinding.ItemSpecieBinding
import com.k0d4black.theforce.models.SpeciePresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class SpecieViewHolder(
    view: View
) : RecyclerViewHolder<SpeciePresentation>(view) {
    val binding = ItemSpecieBinding.bind(view)
    override fun bind(position: Int, item: SpeciePresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}