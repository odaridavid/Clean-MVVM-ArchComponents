package com.k0d4black.theforce.feature.charactersearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.k0d4black.theforce.feature.charactersearch.databinding.FragmentCharacterSearchBinding
import com.k0d4black.theforce.shared.android.base.BindingFragment

class CharacterSearchFragment : BindingFragment<FragmentCharacterSearchBinding>() {

    // region Android Api

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCharacterSearchBinding =
        FragmentCharacterSearchBinding.inflate(layoutInflater, container, false)

    // endregion

    fun bindEvents(){
        // TODO On click of search navigate to results page
    }
}