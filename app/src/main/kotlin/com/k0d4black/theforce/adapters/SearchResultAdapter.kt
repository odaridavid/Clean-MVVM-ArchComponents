package com.k0d4black.theforce.adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.ItemSearchBinding
import com.k0d4black.theforce.models.CharacterPresentation
import kotlinx.android.synthetic.main.item_search.view.*


internal class SearchResultAdapter(
    val onClick: (CharacterPresentation) -> Unit
) : ListAdapter<CharacterPresentation, SearchResultAdapter.SearchedCharacterViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedCharacterViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return SearchedCharacterViewHolder(ItemSearchBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SearchedCharacterViewHolder, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class SearchedCharacterViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(characterPresentation: CharacterPresentation) {
            binding.searchedCharacter = characterPresentation
            binding.executePendingBindings()

            binding.root.more_info_arrow_image_button.setOnClickListener {
                onClick(characterPresentation)
            }
        }
    }

    companion object {
        val DiffUtil =
            object : DiffUtil.ItemCallback<CharacterPresentation>() {
                override fun areItemsTheSame(
                    oldItem: CharacterPresentation,
                    newItem: CharacterPresentation
                ): Boolean = oldItem == newItem


                override fun areContentsTheSame(
                    oldItem: CharacterPresentation,
                    newItem: CharacterPresentation
                ): Boolean = oldItem.url == newItem.url

            }
    }
}