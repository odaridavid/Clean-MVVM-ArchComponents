package com.k0d4black.theforce.shared.android.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.k0d4black.theforce.shared.android.AppScreen

fun FragmentTransaction.commitFragment(
    appScreen: AppScreen,
    block: FragmentTransaction.(Fragment) -> Unit
) {
    val fragment = Class.forName(appScreen.classPath).newInstance() as Fragment
    //TODO Handle error if its not a fragment
    block(fragment)
    addToBackStack(null)
    commit()
}