package com.k0d4black.theforce.features.character_search;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.databinding.SearchResultLayoutItemBinding
import com.k0d4black.theforce.models.SearchedCharacterPresentationModel
import javax.inject.Inject


class SearchResultAdapter @Inject constructor() :
    ListAdapter<SearchedCharacterPresentationModel, SearchResultAdapter.SearchedCharacterViewModel>(
        SearchedCharacterDiffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedCharacterViewModel {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return SearchedCharacterViewModel(SearchResultLayoutItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SearchedCharacterViewModel, position: Int): Unit =
        getItem(position).let { holder.bind(it) }

    inner class SearchedCharacterViewModel(private val binding: SearchResultLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SearchedCharacterPresentationModel) {
            binding.searchedCharacter = model
            binding.executePendingBindings()
        }
    }

    companion object {
        val SearchedCharacterDiffUtil =
            object : DiffUtil.ItemCallback<SearchedCharacterPresentationModel>() {
                override fun areItemsTheSame(
                    oldItem: SearchedCharacterPresentationModel,
                    newItem: SearchedCharacterPresentationModel
                ): Boolean = oldItem.url == newItem.url


                override fun areContentsTheSame(
                    oldItem: SearchedCharacterPresentationModel,
                    newItem: SearchedCharacterPresentationModel
                ): Boolean = oldItem == newItem

            }
    }
}