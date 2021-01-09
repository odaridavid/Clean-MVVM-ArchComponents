package com.k0d4black.theforce.feature.character

import android.view.View
import com.k0d4black.theforce.databinding.ItemSearchBinding
import com.k0d4black.theforce.models.CharacterPresentation
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

internal class SearchedCharacterViewHolder(
    view: View
) : RecyclerViewHolder<CharacterPresentation>(view) {
    val binding = ItemSearchBinding.bind(view)

    override fun bind(position: Int, item: CharacterPresentation) {
        super.bind(position, item)
        binding.executePendingBindings()
    }
}