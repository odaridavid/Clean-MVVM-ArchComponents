package com.k0d4black.theforce.feature.favoritecharacters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.k0d4black.theforce.feature.favoritecharacters.databinding.FragmentFavoritesBinding
import com.k0d4black.theforce.shared.android.base.BindingFragment

class FavoriteCharactersFragment : BindingFragment<FragmentFavoritesBinding>() {

    // region BindingFragment

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoritesBinding =
        FragmentFavoritesBinding.inflate(inflater, container, false)

    // endregion
}