package com.k0d4black.theforce.feature.charactersearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.feature.charactersearch.R
import com.k0d4black.theforce.feature.charactersearch.model.RecentSearchPresentation

internal class RecentSearchAdapter :
    ListAdapter<RecentSearchPresentation, RecentSearchViewHolder>(recentSearchDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recent_search, parent, false)
        return RecentSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(recentSearchPresentation = getItem(position))
    }
}

private val recentSearchDiffUtil = object : DiffUtil.ItemCallback<RecentSearchPresentation>() {

    override fun areItemsTheSame(
        oldItem: RecentSearchPresentation,
        newItem: RecentSearchPresentation
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RecentSearchPresentation,
        newItem: RecentSearchPresentation
    ): Boolean = oldItem.characterName == newItem.characterName
}
