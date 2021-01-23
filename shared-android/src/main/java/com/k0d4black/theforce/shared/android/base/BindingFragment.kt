package com.k0d4black.theforce.shared.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<Binding : ViewBinding> : Fragment() {

    // region Members

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    // endregion

    // region Android Api

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(
            inflater = inflater,
            container = container,
            savedInstanceState = savedInstanceState
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // endregion

    // region Abstract Methods

    abstract fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Binding

    // endregion
}