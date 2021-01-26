package com.k0d4black.theforce.feature.charactersearch.ui


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.k0d4black.theforce.shared.model.RecentSearch

internal class RecentSearchAdapter :
    ListAdapter<RecentSearch, RecentSearchViewHolder>(recentSearchDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

private val recentSearchDiffUtil = object : DiffUtil.ItemCallback<RecentSearch>() {
    override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        TODO("Not yet implemented")
    }
}